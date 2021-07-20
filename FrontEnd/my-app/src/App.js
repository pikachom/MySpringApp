import React from "react";
import { Link, Route, Switch, BrowserRouter as Router } from "react-router-dom";
import { Button } from "antd";
import "antd/dist/antd.css";
import "./App.css";
import Home from "./Home";
import Login from "./Auth/Login";
import Posts from "./posts/Posts";
import AuthRoute from "./Auth/AuthRoute";
import RegisterToken from "./Auth/RegisterToken";
import { authorizationInfo, userInfo } from "./Auth/AuthStore";
import { useCookies, withCookies, Cookies } from "react-cookie";

const App = () => {
  return (
    <Router>
      <header>
        <Link to="/">
          <Button>Home</Button>
        </Link>
        <Link to="/posts">
          <Button>Posts</Button>
        </Link>
      </header>
      <hr />
      <main>
        <Switch>
          <Route exact path="/" component={Home} />
          <Route path="/login" component={Login} />
          <Route path="/set-token/:token" component={RegisterToken} />
          <AuthRoute path="/posts" render={() => <Posts />} />
        </Switch>
      </main>
    </Router>
  );
};

export default App;
