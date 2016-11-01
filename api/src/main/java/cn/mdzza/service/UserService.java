package cn.mdzza.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ydt on 2016/11/1.
 */
@Service
@Transactional(readOnly = true)
public class UserService {
}
