package com.jgk.fdb.domain.usertype;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;

public class FakeUserType implements UserType {
    public FakeUserType() {
        
    }

    public static void main(String[] args) {
    }
    @Override
    public int[] sqlTypes() {
        return new int[] {StringType.INSTANCE.getSqlTypeDescriptor().getSqlType()};
    }

    @Override
    public Class returnedClass() {
        
        return FakeUserType.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names,
            SessionImplementor session, Object owner)
            throws HibernateException, SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index,
            SessionImplementor session) throws HibernateException, SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isMutable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object assemble(Serializable cached, Object owner)
            throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        // TODO Auto-generated method stub
        return null;
    }

}
