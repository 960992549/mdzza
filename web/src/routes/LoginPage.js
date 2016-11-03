import React from 'react';
import {connect} from 'dva';
import LoginForm from '../components/LoginForm';
import styles from './LoginPage.css'

function LoginPage() {
  return (
    <div className={styles.normal}>
      <LoginForm />
    </div>
  );
}

LoginPage.propTypes = {

};

export default connect()(LoginPage);
