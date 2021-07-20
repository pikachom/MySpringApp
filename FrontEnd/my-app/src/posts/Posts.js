import React from "react";
import { Table } from "antd";
import "antd/dist/antd.css";

const columns = [
  {
    title: "번호",
    dataIndex: "number",
  },
  {
    title: "제목",
    dataIndex: "title",
  },
  {
    title: "작성자",
    dataIndex: "name",
  },
];

const data = [];
for (let i = 0; i < 46; i++) {
  data.push({
    number: i,
    title: `title ${i}`,
    name: `Edward King ${i}`,
  });
}

const Posts = () => {
  return (
    <div>
      <Table columns={columns} dataSource={data} />
    </div>
  );
};

export default Posts;
