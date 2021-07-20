import axios from "axios";
import { observable, runInAction, toJS } from "mobx";
import jwt_decode from "jwt-decode";

let testMethods = observable({
  user: () => {
    axios
      .get("http://localhost:8080/api/user")
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log("error");
      });
  },

  hello: () => {
    axios
      .get("http://localhost:8080/api/hello")
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log("error");
      });
  },
});

export default testMethods;
