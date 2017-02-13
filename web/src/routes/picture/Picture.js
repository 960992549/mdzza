import React from 'react';
import { connect } from 'dva';
import Pic from '../../components/picture/Picture';

const Picture = ({ dispatch, picture}) => {

  const pictureProps = {
    picture
  };

  return (
    <div>
      <Pic {...pictureProps}/>
    </div>
  );
};

function mapStateToProps({ picture }) {
  return { picture };
}

export default connect(mapStateToProps)(Picture);
