import React from 'react';
import { Layout as AntLayout } from 'antd';
import Header from './Header';
import Sider from './Sider';
import Content from './Content';
import Footer from './Footer';

function Layout({ children }) {

  return (
    <AntLayout>
      <AntLayout.Header>
        <Header />
      </AntLayout.Header>
      <AntLayout>
        <AntLayout.Sider>
          <Sider />
        </AntLayout.Sider>
        <AntLayout.Content>
          <Content children={children} />
        </AntLayout.Content>
      </AntLayout>
      <AntLayout.Footer>
        <Footer />
      </AntLayout.Footer>
    </AntLayout>
  );
}

export default Layout;
