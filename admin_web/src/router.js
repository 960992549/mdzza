import React from 'react';
import { Router, Route, IndexRoute } from 'dva/router';
import Login from './routes/Login';
import Layout from './routes/layout/Layout';
import Index from './routes/Index';
import Apis from './routes/api/Apis';
import Api from './routes/api/Api';
import ApiInputs from './routes/api/ApiInputs';
import ApiOutputs from './routes/api/ApiOutputs';

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
        </Route>
      </Route>
    </Router>
  );
}

export default RouterConfig;
