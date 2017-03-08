import React from 'react';
import { connect } from 'dva';
import { Link } from 'dva/router';
import { Row, Col, Input, Form, Button, InputNumber } from 'antd';

function ApiInputs({ dispatch, apiInputs }) {
  const { list } = apiInputs;

  const FormInputs = Form.create()(({
    form: {
      getFieldDecorator,
      validateFields,
      getFieldsValue,
      getFieldValue,
      setFieldsValue,
    }
  }) => {
    function add() {
      changeList();
      list.push({ name: '', label: '', dataType: '', description: '', sort : '' });
      dispatch({ type: 'apiInputs/setState', payload: { list }});
    }

    function save(e) {
      e.preventDefault();
      validateFields((error, values) => {
        if(!error) {
          changeList();
          dispatch({ type: 'apiInputs/setState', payload: { list }});
          dispatch({ type: 'apiInputs/save', payload: values });
        }
      });
    }

    function del(index) {
      changeList();
      list.splice(index, 1);
      dispatch({ type: 'apiInputs/setState', payload: { list }});
    }

    //修改list的值为form表单中的数据
    function changeList() {
      const fields = ['name', 'label', 'dataType', 'description', 'sort'];
      for(let i = 0; i < list.length; i++) {
        for(let j = 0; j < fields.length; j++) {
          list[i][fields[j]] = getFieldValue(fields[j] + "_" + i);
        }
      }
    }

    const Inputs = list.map((data, index) => {
      return (
        <Row key={index}>
          <Col span={4}>
            <Form.Item label="参数名">
              {getFieldDecorator('name_' + index,{
                initialValue: data.name,
                rules: [{ required: true, message: '请输入参数名' }],
              })(<Input />)}
            </Form.Item>
          </Col>
          <Col span={4}>
            <Form.Item label="标签">
              {getFieldDecorator('label_' + index,{
                initialValue: data.label,
                rules: [{ required: true, message: '请输入标签' }],
              })(<Input />)}
            </Form.Item>
          </Col>
          <Col span={4}>
            <Form.Item label="数据类型">
              {getFieldDecorator('dataType_' + index,{
                initialValue: data.dataType,
                rules: [{ required: true, message: '请输入数据类型' }],
              })(<Input />)}
            </Form.Item>
          </Col>
          <Col span={4}>
            <Form.Item label="说明">
              {getFieldDecorator('description_' + index,{
                initialValue: data.description,
              })(<Input />)}
            </Form.Item>
          </Col>
          <Col span={4}>
            <Form.Item label="排序">
              {getFieldDecorator('sort_' + index,{
                initialValue: data.sort,
                rules: [{ required: true, message: '请输入排序' }],
              })(<InputNumber min={0} step={10} />)}
            </Form.Item>
          </Col>
          <Col span={4}>
            <Form.Item>
              <Link to={"/api/apiInputValidators" + data.id}>配置校验规则</Link>
              <span className="ant-divider" />
              <a onClick={()=>del(index)}>删除</a>
            </Form.Item>
          </Col>
        </Row>
      );
    });

    return (
      <Form inline>
        {Inputs}
        <Form.Item>
          <Button onClick={add}>
            添加入参
          </Button>
          <Button onClick={save}>
            保存配置
          </Button>
        </Form.Item>
      </Form>
    );
  });

  return (
    <div>
      <FormInputs/>
    </div>
  );
}

function mapStateToProps({ apiInputs }) {
  return { apiInputs };
}

export default connect(mapStateToProps)(ApiInputs);
