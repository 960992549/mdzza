import React from 'react';
import { Router, Route, IndexRoute } from 'dva/router';
import Login from './routes/Login';
import Layout from './routes/layout/Layout';
import Index from './routes/Index';
import Apis from './routes/api/Apis';
import Api from './routes/api/Api';
import ApiInputs from './routes/api/ApiInputs';
import ApiOutputs from './routes/api/ApiOutputs';
import ApiInputValidators from './routes/api/ApiInputValidators';
import ApiOutputFormats from './routes/api/ApiOutputFormats';
import SysRoles from './routes/sys/SysRoles';
import SysRole from './routes/sys/SysRole';
import SysRolePermission from './routes/sys/SysRolePermission';

function RouterConfig({ history }) {
  return (
    <Router history={history}>
      <Route path="/login" component={Login} />
      <Route path="/" component={Layout}>
        <IndexRoute component={Index}/>
        <Route path="api">
          <Route path="list" component={Apis}/>
          <Route path=":id" component={Api}/>
          <Route path="add" component={Api}/>
          <Route path="apiInputs/:apiId" component={ApiInputs}/>
          <Route path="apiOutputs/:apiId" component={ApiOutputs}/>
          <Route path="apiInput/apiInputValidators/:inputId" component={ApiInputValidators}/>
          <Route path="apiOutput/apiOutputFormats/:outputId" component={ApiOutputFormats}/>
        </Route>
        <Route path="sys">
          <Route path="role">
            <Route path="list" component={SysRoles}/>
            <Route path=":id" component={SysRole}/>
            <Route path="add" component={SysRole}/>
            <Route path=":id/permission" component={SysRolePermission}/>
          </Route>
        </Route>
      </Route>
    </Router>
  );
}

export default RouterConfig;
