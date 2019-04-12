package com.ouyangjia.mybatis;

import com.ouyangjia.mybatis.dao.LoanApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanApplyService{
    @Autowired
    private LoanApplyMapper loanApplyMapper;

    public LoanApply getByLoanNo(String loanNo) {
        return loanApplyMapper.queryByLoanNo(loanNo);
    }
}
