import React from 'react';
import { connect } from 'dva';
import { Row, Col, Input } from 'antd';

function ApiOutputs({ dispatch, apiOutputs }) {
  const { list } = apiOutputs;

  const Outputs = list.map((data) => {
    return (
      <Row>
        <Col span={2}>data.name</Col>
        <Col span={2}>data.label</Col>
        <Col span={2}>data.dataType</Col>
        <Col span={4}>data.description</Col>
        <Col span={2}>data.sort</Col>
      </Row>
    );
  });

  return (
    <div>
      <Outputs/>
    </div>
  );
}

function mapStateToProps({ apiOutputs }) {
  return { apiOutputs };
}

export default connect(mapStateToProps)(ApiOutputs);
