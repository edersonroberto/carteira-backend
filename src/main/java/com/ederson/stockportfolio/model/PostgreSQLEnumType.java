package com.ederson.stockportfolio.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

import com.ederson.stockportfolio.enums.OperationType;

public class PostgreSQLEnumType extends org.hibernate.type.EnumType<OperationType> {

	private static final long serialVersionUID = -7117152818845951970L;
	
	  public void nullSafeSet(
	            PreparedStatement st, 
	            Object value, 
	            int index, 
	            SharedSessionContractImplementor session) 
	        throws HibernateException, SQLException {
	        if(value == null) {
	            st.setNull( index, Types.OTHER );
	        }
	        else {
	            st.setObject( 
	                index, 
	                value.toString(), 
	                Types.OTHER 
	            );
	        }
	    }

}
