import React from 'react';
import { connect } from 'dva';
import { Link } from 'dva/router';
import { Table  } from 'antd';

function Apis({ apis }) {

  const columns = [{
    title: '编号',
    dataIndex: 'id',
    key: 'id',
    width: '10%',
  },{
    title: '名称',
    dataIndex: 'name',
    key: 'name',
    width: '15%',
  },{
    title: '模块',
    dataIndex: 'module',
    key: 'module',
    width: '10%',
  },{
    title: '资源',
    dataIndex: 'resource',
    key: 'resource',
    width: '10%',
  },{
    title: '方法',
    dataIndex: 'method',
    key: 'method',
    width: '15%',
  },{
    title: '调用方法',
    dataIndex: 'invokeMethod',
    key: 'invokeMethod',
    width: '15%',
  },{
    title: '操作',
    key: 'action',
    render: (text, record) => (
      <span>
        <Link to={'/api/' + record.id}>查看</Link>
        <span className="ant-divider" />
        <Link to={'/api/apiInputs/' + record.id}>配置入参</Link>
        <span className="ant-divider" />
        <Link to={'/api/apiOutputs/' + record.id}>配置出参</Link>
      </span>
    ),
  }];

  return (
    <div>
      <Table rowKey={record => record.id} dataSource={apis.list} columns={columns} />
    </div>
  );
}

// 指定订阅数据
function mapStateToProps({ apis }) {
  return {apis};
};

export default connect(mapStateToProps)(Apis);
