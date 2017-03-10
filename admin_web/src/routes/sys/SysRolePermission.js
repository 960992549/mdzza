import React from 'react';
import { connect } from 'dva';

function SysRolePermission({ dispatch, sysRolePermission }) {

  return (
    <div></div>
  );
}

function mapStateToProps({ sysRolePermission }) {
  return {sysRolePermission};
}

export default connect(mapStateToProps)(SysRolePermission);
