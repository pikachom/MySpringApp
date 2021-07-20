import React, { useEffect } from "react";
import { Route, Redirect } from "react-router-dom";
import { authorizationInfo, userInfo } from "./AuthStore";

const AuthRoute = ({ component: Component, render, ...rest }) => {
  return (
    <Route
      {...rest}
      render={(props) =>
        authorizationInfo.isAuthorized ? (
          render ? (
            render(props)
          ) : (
            <Component {...props} />
          )
        ) : (
          <Redirect
            to={{ pathname: "/login", state: { from: props.location } }}
          />
        )
      }
    />
  );
};

export default AuthRoute;
