import React from "react";
import { Row, Col } from "antd";
import "antd/dist/antd.css";

const Post = () => {
  return (
    <div>
      <Row>
        <Col span={4}>제목</Col>
        <Col span={20}>제목</Col>
      </Row>
      <Row>
        <Col span={4}>작성자</Col>
        <Col span={20}>작성자</Col>
      </Row>
      <Row>
        <Col span={4}>내용</Col>
        <Col span={20}>내용</Col>
      </Row>
    </div>
  );
};

export default Post;
