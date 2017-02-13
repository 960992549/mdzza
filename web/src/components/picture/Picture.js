import React from 'react';
import { Button } from 'antd';

const Picture = ({ picture }) => {
  return (
    <div>
      this picture {picture.id} - {picture.name}
    </div>
  );
};

export default Picture;
