import React from 'react';
import { connect } from 'dva';

const PictureList = ({ pictureList }) => {
  return (
    <div>
      pictureList
      {pictureList.pictureList}
    </div>
  );
};

function mapStateToProps({ pictureList }) {
  return { pictureList }
}

export default connect(mapStateToProps)(PictureList);
