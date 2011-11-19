package com.jgk.database;

import static org.junit.Assert.assertEquals;

import java.sql.Types;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.BigIntegerType;
import org.hibernate.type.BinaryType;
import org.hibernate.type.BlobType;
import org.hibernate.type.CharArrayType;
import org.hibernate.type.CharacterType;
import org.hibernate.type.ClobType;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.FloatType;
import org.hibernate.type.ImageType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LocaleType;
import org.hibernate.type.LongType;
import org.hibernate.type.ShortType;
import org.hibernate.type.StringType;
import org.hibernate.type.TextType;
import org.hibernate.type.TimeType;
import org.hibernate.type.TimeZoneType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.TrueFalseType;
import org.hibernate.type.UrlType;
import org.hibernate.type.YesNoType;
import org.junit.Test;

public class CheckSqlTypes {

    @Test
    public void testin() {
        showTypes();
        showHibTypes();
        // System.out.println(Types.VARCHAR);
        // System.out.println(StringType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.VARCHAR, StringType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.INTEGER, IntegerType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.DOUBLE, DoubleType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.NUMERIC, BigDecimalType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.NUMERIC, BigIntegerType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.INTEGER, IntegerType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.SMALLINT, ShortType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.VARCHAR, StringType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.DOUBLE, DoubleType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.VARBINARY, BinaryType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.BLOB, BlobType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.CHAR, CharacterType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.VARCHAR, CharArrayType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.CLOB, ClobType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.DATE, DateType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.FLOAT, FloatType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.LONGVARBINARY, ImageType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.VARCHAR, LocaleType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.BIGINT, LongType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.LONGVARCHAR, TextType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.TIMESTAMP, TimestampType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.TIME, TimeType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.VARCHAR, TimeZoneType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.CHAR, TrueFalseType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.CHAR, YesNoType.INSTANCE.getSqlTypeDescriptor().getSqlType());
        assertEquals(Types.VARCHAR, UrlType.INSTANCE.getSqlTypeDescriptor().getSqlType());
    }
    
    void showHibTypes() {
        MT[] mts = new MT[] {
                new MT(BigDecimalType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"BigDecimalType-NUMERIC"),
                new MT(BigIntegerType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"BigIntegerType-NUMERIC"),
                new MT(IntegerType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"IntegerType-INTEGER"),
                new MT(ShortType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"ShortType-SMALLINT"),
                new MT(StringType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"StringType-VARCHAR"),
                new MT(DoubleType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"DoubleType-DOUBLE"),
                new MT(BinaryType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"BinaryType-VARBINARY"),
                new MT(BlobType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"BlobType-BLOB"),
                new MT(CharacterType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"CharacterType-CHAR"),
                new MT(CharArrayType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"CharArrayType-VARCHAR"),
                new MT(ClobType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"ClobType-CLOB"),
                new MT(DateType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"DateType-DATE"),
                new MT(FloatType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"FloatType-FLOAT"),
                new MT(ImageType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"ImageType-LONGVARBINARY"),
                new MT(LocaleType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"LocaleType-VARCHAR"),
                new MT(LongType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"LongType-BIGINT"),
                new MT(TextType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"TextType-LONGVARCHAR"),
                new MT(TimestampType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"TimestampType-TIMESTAMP"),
                new MT(TimeType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"TimeType-TIME"),
                new MT(TimeZoneType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"TimeZoneType-VARCHAR"),
                new MT(TrueFalseType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"TrueFalseType-CHAR"),
                new MT(YesNoType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"YesNoType-CHAR"),
                new MT(UrlType.INSTANCE.getSqlTypeDescriptor().getSqlType(),"UrlType-VARCHAR"),
        };
        System.out.println("Hibernate Types");
        for (MT mt : mts) {
            System.out.printf("%-30s: %d\n", mt.name, mt.type);

        }

    }

    void showTypes() {
        MT[] mts = new MT[] { 
                new MT(Types.ARRAY, "ARRAY"), 
                new MT(Types.BIGINT, "BIGINT"), 
                new MT(Types.BINARY, "BINARY"), 
                new MT(Types.BIT, "BIT"), 
                new MT(Types.BLOB, "BLOB"), 
                new MT(Types.BOOLEAN, "BOOLEAN"), 
                new MT(Types.CHAR, "CHAR"), 
                new MT(Types.CLOB, "CLOB"), 
                new MT(Types.DATALINK, "DATALINK"), 
                new MT(Types.DATE, "DATE"), 
                new MT(Types.DECIMAL, "DECIMAL"), 
                new MT(Types.DISTINCT, "DISTINCT"), 
                new MT(Types.DOUBLE, "DOUBLE"), 
                new MT(Types.FLOAT, "FLOAT"), 
                new MT(Types.INTEGER, "INTEGER"), 
                new MT(Types.JAVA_OBJECT, "JAVA_OBJECT"), 
                new MT(Types.LONGNVARCHAR, "LONGNVARCHAR"), 
                new MT(Types.LONGVARBINARY, "LONGVARBINARY"), 
                new MT(Types.LONGVARCHAR, "LONGVARCHAR"), 
                new MT(Types.NCHAR, "NCHAR"), 
                new MT(Types.NCLOB, "NCLOB"), 
                new MT(Types.NULL, "NULL"), 
                new MT(Types.NUMERIC, "NUMERIC"), 
                new MT(Types.NVARCHAR, "NVARCHAR"), 
                new MT(Types.OTHER, "OTHER"), 
                new MT(Types.REAL, "REAL"), 
                new MT(Types.REF, "REAL"), 
                new MT(Types.ROWID, "ROWID"), 
                new MT(Types.SMALLINT, "SMALLINT"), 
                new MT(Types.SQLXML, "SQLXML"), 
                new MT(Types.STRUCT, "STRUCT"), 
                new MT(Types.TIME, "TIME"), 
                new MT(Types.TIMESTAMP, "TIMESTAMP"), 
                new MT(Types.TINYINT, "TINYINT"), 
                new MT(Types.VARBINARY, "VARBINARY"), 
                new MT(Types.VARCHAR, "VARCHAR"), 
        };
        System.out.println("SQL Types");
        for (MT mt : mts) {
            System.out.printf("%-20s: %d\n", mt.name, mt.type);
        }
    }

    private class MT {
        private int type;
        private String name;
        private AbstractSingleColumnStandardBasicType<?> hibType;

        public MT(int type, String name) {
            this.type = type;
            this.name = name;
        }
        
    }
}
