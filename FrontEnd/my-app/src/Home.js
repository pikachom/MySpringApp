import React, { useState, useEffect, useCallback, useRef } from "react";
import "antd/dist/antd.css";
import { Link, Route, Switch, BrowserRouter as Router } from "react-router-dom";
import { Button } from "antd";
import authMethods, { authorizationInfo, userInfo } from "./Auth/AuthStore";
import { useObserver, observer } from "mobx-react";
import testMethods from "./Test/TestStore";

const Home = () => {
  return useObserver(() => (
    <>
      <h1>Home</h1>
      <p>Welcome home!</p>
      {!authorizationInfo.isAuthorized ? (
        <Link to="/login">
          <Button>Login</Button>
        </Link>
      ) : (
        <span />
      )}
      {!authorizationInfo.isAuthorized ? (
        <Button href="http://localhost:8080/oauth2/authorization/google">
          Login via Google
        </Button>
      ) : (
        <span />
      )}
      {authorizationInfo.isAuthorized ? (
        <div>안녕하세요 {userInfo.sub} 님</div>
      ) : (
        <span />
      )}
      {authorizationInfo.isAuthorized ? (
        <Button onClick={authMethods.logout}>로그아웃</Button>
      ) : (
        <span />
      )}
      <Button onClick={testMethods.hello}>hello</Button>
      <Button onClick={testMethods.user}>user</Button>
    </>
  ));
};

export default Home;
