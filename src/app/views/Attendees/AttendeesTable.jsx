import React, { memo, useState, useEffect, useContext } from "react";
import styles from "./_attendees.module.scss";
import { getAllAttendees } from "./AttendeeService";
import { toast } from "react-toastify";

function AttendeesTable() {
  //   const [attendees, setAttendees] = useState([{ id: 1, name: 'John', yearOfBirth: 1990, sex: 'Male', school: 'ABC University', major: 'Computer Science', username: 'Longnd' },
  //   { id: 2, name: 'Jane', yearOfBirth: 1995, sex: 'Female', school: 'XYZ College', major: 'Business', username: 'Longnd2' },]);
  const [attendees, setAttendees] = useState([]);

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
        console.log("Server error");
        toast.warning("Server error");
      });
  };

  return (
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
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default memo(AttendeesTable);
