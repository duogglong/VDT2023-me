import React, { memo, useState, useEffect } from "react";
import styles from "./_attendees.module.scss";
import { getAllAttendees, getById } from "./AttendeeService";
import { FaPencilAlt, FaTrash } from "react-icons/fa";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function AttendeesTable() {
  const [attendees, setAttendees] = useState([]);
  const [currentAttendeeEdit, setCurrentAttendeeEdit] = useState(null);

  useEffect(() => {
    handleLoadPageData();
  }, []);

  const handleLoadPageData = () => {
    console.log("Call handleLoadPageData");
    getAllAttendees()
      .then((res) => {
        console.log(res);
        if (res?.data) {
          console.log(res.data);
          setAttendees(res.data);
          return;
        }
        throw Error(res.status);
      })
      .catch(function (error) {
        toast.warning("Server error");
      });
  };

  const handleViewAttendee = (id) => {
    console.log(`View attendee with ID ${id}`);
    getById(id)
      .then((res) => {
        console.log(res);
        if (res?.data) {
          console.log(res.data);
          //   setAttendees(res.data);
          return;
        }
        throw Error(res.status);
      })
      .catch(function (error) {
        console.log(error);
        toast.warning("Server error");
      });
  };

  const handleEditAttendee = (id) => {
    console.log(`Edit attendee with ID ${id}`);
    getById(id)
      .then((res) => {
        console.log(res);
        if (res?.data) {
          console.log(res.data);
          setCurrentAttendeeEdit(res.data);
          return;
        }
        throw Error(res.status);
      })
      .catch(function (error) {
        toast.warning("Server error");
      });

    console.log(currentAttendeeEdit);
  };

  const handleDeleteAttendee = (id) => {
    console.log(`Delete attendee with ID ${id}`);
    toast.success("Delete success");
  };

  return (
    <>
      <table className={styles.attendeesTable}>
        <thead>
          <tr>
            <th>STT</th>
            <th>Name</th>
            <th>Username</th>
            <th>Year of Birth</th>
            <th>Sex</th>
            <th>School</th>
            <th>Major</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {attendees.map((attendee, index) => (
            <tr key={attendee.id}>
              <td>{index + 1}</td>
              <td>{attendee.name}</td>
              <td>{attendee.username}</td>
              <td>{attendee.yearOfBirth}</td>
              <td>{attendee.sex}</td>
              <td>{attendee.school}</td>
              <td>{attendee.major}</td>
              <td className={styles.iconBox}>
                <FaPencilAlt
                  className={styles.iconUpdate}
                  onClick={() => handleEditAttendee(attendee.id)}
                />
                <FaTrash
                  className={styles.iconDelete}
                  onClick={() => handleDeleteAttendee(attendee.id)}
                />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <ToastContainer />
    </>
  );
}

export default memo(AttendeesTable);
