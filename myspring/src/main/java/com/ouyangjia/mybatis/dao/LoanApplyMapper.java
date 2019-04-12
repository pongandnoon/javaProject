package com.ouyangjia.mybatis.dao;

import com.ouyangjia.mybatis.LoanApply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

public interface LoanApplyMapper {

    public LoanApply queryByLoanNo(@Param("loanNo") String loanNo);
}
