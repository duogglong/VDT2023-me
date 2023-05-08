import axios from 'axios';
import { API_ENPOINT } from '../../../Constraint';
const API_ATTENDEES = API_ENPOINT + "/attendees";

export const getAllAttendees = () => {
    let apiPath = API_ATTENDEES + "/all";
    console.log("Call api:" + apiPath);
    return axios.get(apiPath);
};
