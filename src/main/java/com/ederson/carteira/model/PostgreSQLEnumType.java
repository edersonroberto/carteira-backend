package com.ederson.carteira.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

import com.ederson.carteira.enums.TipoOperacao;

public class PostgreSQLEnumType extends org.hibernate.type.EnumType<TipoOperacao> {

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
