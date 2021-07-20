import React, { useEffect } from "react";
import authMethods, { authorizationInfo, test } from "./AuthStore";
import { Route, Redirect } from "react-router-dom";
import { runInAction, toJS } from "mobx";
import { useObserver, observer } from "mobx-react";

const RegisterToken = observer(
  ({ match, component: Component, render, ...rest }) => {
    useEffect(() => {
      authMethods.setDefaultAxiosRequestHeader(match.params.token);
      authMethods.setUserInfo(match.params.token);
      authMethods.setAuthorizationInfo(true);
    });
    return (
      <Redirect
        to={{
          pathname: "/",
        }}
      />
    );
  }
);

export default RegisterToken;
