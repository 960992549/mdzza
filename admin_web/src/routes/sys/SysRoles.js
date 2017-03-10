import React from 'react';
import { connect } from 'dva';
import { Link } from 'dva/router';
import { Table, Popconfirm } from 'antd';

function SysRoles({ dispatch, sysRoles}) {

  function del(id) {
    dispatch({ type: 'sysRoles/del', payload: { id } });
  }

  const columns = [{
    title: '序号',
    key: 'index',
    width: '5%',
    render: (text, record, index) => (
      <span>{index + 1}</span>
    ),
  }, {
    title: '编号',
    dataIndex: 'id',
    key: 'id',
    width: '10%',
  }, {

  }, {
    title: '英文名称',
    dataIndex: 'nameEn',
    key: 'nameEn',
    width: '10%',
  }, {
    title: '中文名称',
    dataIndex: 'nameCn',
    key: 'nameCn',
    width: '10%'
  }, {
    title: '操作',
    key: 'action',
    render: (text, record) => (
      <span>
        <Link to={"/sys/role/" + record.id}>查看</Link>
        <span className="ant-divider" />
        <Link to={"/sys/role/configPermission/" + record.id}>配置权限</Link>
        <span className="ant-divider" />
        <Popconfirm title="确认删除此角色吗？" onConfirm={()=>del(record.id)}><a>删除</a></Popconfirm>
      </span>
    ),
  }];

  return (
    <Table rowKey={record=>record.id} dataSource={sysRoles.list} columns={columns} />
  );
}

function mapStateToProps({ sysRoles }) {
  return {sysRoles};
}

export default connect(mapStateToProps)(SysRoles);
