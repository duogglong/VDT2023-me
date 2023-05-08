import React, { memo, useContext, useState, useEffect } from "react";
import styles from "./_attendee_dialog.module.scss";
import { ThemeContext } from "../AttendeesTable";
import { MdClose } from "react-icons/md";
import { getById } from "../AttendeeService";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function AttendeeDialog() {
  const providerValue = useContext(ThemeContext);
  const [attendee, setAttendee] = useState(null);

  useEffect(() => {
    let id = providerValue.id;
    console.log("Current edit id: " + id);

    getById(id)
      .then((res) => {
        console.log(res);
        if (res?.data) {
          console.log(res.data);
          setAttendee(res.data);
          return;
        }
        throw Error(res.status);
      })
      .catch(function (error) {
        toast.warning("Server error");
      });
  }, []);

  const handleClose = () => {
    providerValue.setIsOpenFormInput(false);
  };

  const handleSubmit = () => {};

  return (
    <div className={styles.container}>
      <div className={styles.dialog}>
        {/* icon delete */}
        <div className={styles.box} onClick={() => handleClose()}>
          <MdClose className={styles.icon} />
        </div>
        {/* Title */}
        {!attendee && <h2 className={styles.title}>NEW STUDENT</h2>}
        {attendee && <h2 className={styles.title}>EDIT STUDENT</h2>}
        {/* Content */}
        <div className={styles.content}></div>
        <div className={styles.contentBox}>
          <h1>{attendee?.name}</h1>
        </div>
        {/* Save */}
        <div className={styles.footer}>
          <button
            type="button"
            className={styles.btn}
            onClick={() => handleSubmit()}
          >
            SAVE
          </button>
        </div>
      </div>
    </div>
  );

  //   return (
  //     <div className={styles.container}>
  //       <div className={styles.dialog}>
  //         {/* icon delete */}
  //         <div className={styles.box} onClick={() => handleClose()}>
  //           <MdClose className={styles.icon} />
  //         </div>
  //         {/* Title */}
  //         {!id && <h2 className={styles.title}>NEW STUDENT</h2>}
  //         {id && <h2 className={styles.title}>EDIT STUDENT</h2>}
  //         {/* Content */}
  //         <div className={styles.content}></div>

  //             <h1>sdfds</h1>
  //         {/* Save */}
  //         {/* <div className={styles.footer}>
  //           <button
  //             type="button"
  //             className={styles.btn}
  //             onClick={() => handleSubmit()}
  //           >
  //             SAVE
  //           </button>
  //         </div> */}
  //       </div>
  //     </div>
  //   );
}

export default memo(AttendeeDialog);
