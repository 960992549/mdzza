import React from 'react';
import { Link } from 'react-router';
import styles from './Navigation.less';

const Navigation = ({ children }) => {
  return (
    <div>
      <div>
        <Link to="/picture" activeClassName={styles.active}>图片</Link>
        <Link to="/joke" activeClassName={styles.active}>段子</Link>
        <Link to="/article" activeClassName={styles.active}>文章</Link>
      </div>
      <div>
        {children}
      </div>
    </div>
  );
};

export default Navigation;
