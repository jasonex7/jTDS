//
// Copyright 1998 CDS Networks, Inc., Medford Oregon
//
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
// 1. Redistributions of source code must retain the above copyright
//    notice, this list of conditions and the following disclaimer.
// 2. Redistributions in binary form must reproduce the above copyright
//    notice, this list of conditions and the following disclaimer in the
//    documentation and/or other materials provided with the distribution.
// 3. All advertising materials mentioning features or use of this software
//    must display the following acknowledgement:
//      This product includes software developed by CDS Networks, Inc.
// 4. The name of CDS Networks, Inc.  may not be used to endorse or promote
//    products derived from this software without specific prior
//    written permission.
//
// THIS SOFTWARE IS PROVIDED BY CDS NETWORKS, INC. ``AS IS'' AND
// ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED.  IN NO EVENT SHALL CDS NETWORKS, INC. BE LIABLE
// FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
// DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
// OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
// HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
// LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
// OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE.
//


package com.internetcds.jdbc.tds;

import java.sql.*;


/**
 * A ResultSetMetaData object can be used to find out about the types
 * and properties of the columns in a ResultSet.
 *
 * @author Craig Spannring
 * @version $Id: TdsResultSetMetaData.java,v 1.6 2002-08-28 07:44:24 alin_sinpalean Exp $
 */
public class TdsResultSetMetaData implements java.sql.ResultSetMetaData
{
   public static final String cvsVersion = "$Id: TdsResultSetMetaData.java,v 1.6 2002-08-28 07:44:24 alin_sinpalean Exp $";


   /**
    * Does not allow NULL values.
    */
   public static final int columnNoNulls = 0;

   /**
    * Allows NULL values.
    */
   public static final int columnNullable = 1;

   /**
    * Nullability unknown.
    */
   public static final int columnNullableUnknown = 2;


   private Columns  columnsInfo;


   private void NotImplemented()
      throws SQLException
   {

      throw new SQLException("Not implemented");
   }

   public TdsResultSetMetaData(Columns columns_)
   {
      columnsInfo = columns_;
   }


   /**
    * What's a column's table's catalog name?
    *
    * @param column the first column is 1, the second is 2, ...
    * @return column name or "" if not applicable.
    * @exception SQLException if a database-access error occurs.
    */
   public String getCatalogName(int column) throws SQLException
   {
      return "";
   }


   /**
    * What's the number of columns in the ResultSet?
    *
    * @return the number
    * @exception SQLException if a database-access error occurs.
    */
   public int getColumnCount() throws SQLException
   {
      return columnsInfo.fakeColumnCount();
   }


   /**
    * What's the column's normal max width in chars?
    *
    * @param column the first column is 1, the second is 2, ...
    * @return max width
    * @exception SQLException if a database-access error occurs.
    */
   public int getColumnDisplaySize(int column) throws SQLException
   {
      return columnsInfo.getDisplaySize(column);
   }


   /**
    * What's the suggested column title for use in printouts and
    * displays?
    *
    * @param column the first column is 1, the second is 2, ...
    * @return true if so
    * @exception SQLException if a database-access error occurs.
    */
   public String getColumnLabel(int column) throws SQLException
   {
      return columnsInfo.getLabel(column);
   }


   /**
    * What's a column's name?
    *
    * @param column the first column is 1, the second is 2, ...
    * @return column name
    * @exception SQLException if a database-access error occurs.
    */
   public String getColumnName(int column) throws SQLException
   {
      return columnsInfo.getName(column);
   }


   /**
    * What's a column's SQL type?
    *
    * @param column the first column is 1, the second is 2, ...
    * @return SQL type
    * @exception SQLException if a database-access error occurs.
    */
   public int getColumnType(int column) throws SQLException
   {
        return columnsInfo.getJdbcType( column );
   }


   /**
    * What's a column's data source specific type name?
    *
    * @param column the first column is 1, the second is 2, ...
    * @return type name
    * @exception SQLException if a database-access error occurs.
    */
   public String getColumnTypeName(int column) throws SQLException
   {
      String result = null;

      switch (columnsInfo.getNativeType(column))
      {
         case Tds.SYBVOID: {result = "void"; break;}
         case Tds.SYBIMAGE: {result = "image"; break;}
         case Tds.SYBTEXT: {result = "text"; break;}
         case Tds.SYBUNIQUEID: {result = "uniqueidentifier"; break;}
         case Tds.SYBVARBINARY: {result = "varbinary"; break;}
         case Tds.SYBINTN: {result = "INTN"; break;}
         case Tds.SYBVARCHAR: {result = "varchar"; break;}
         case Tds.SYBBINARY: {result = "binary"; break;}
         case Tds.SYBCHAR: {result = "char"; break;}
         case Tds.SYBINT1: {result = "tinyint"; break;}
         case Tds.SYBBIT: {result = "bit"; break;}
         case Tds.SYBINT2: {result = "smallint"; break;}
         case Tds.SYBINT4: {result = "int"; break;}
         case Tds.SYBDATETIME4: {result = "smalldatetime"; break;}
         case Tds.SYBREAL: {result = "real"; break;}
         case Tds.SYBMONEY: {result = "money"; break;}
         case Tds.SYBDATETIME: {result = "datetime"; break;}
         case Tds.SYBFLT8: {result = "float"; break;}
         case Tds.SYBDECIMAL: {result = "decimal"; break;}
         case Tds.SYBNUMERIC: {result = "numeric"; break;}
         case Tds.SYBFLTN: {result = "FLTN"; break;}
         case Tds.SYBMONEYN: {result = "MONEYN"; break;}
         case Tds.SYBDATETIMN: {result = "DATETIMN"; break;}
         case Tds.SYBMONEY4: {result = "smallmoney"; break;}
         default:
         {
            throw new SQLException("Unknown native type for column " + column);
         }
      }
      return result;
   }


   /**
    * What's a column's number of decimal digits?
    *
    * @param column the first column is 1, the second is 2, ...
    * @return precision
    * @exception SQLException if a database-access error occurs.
    */
   public int getPrecision(int column) throws SQLException
   {
      return columnsInfo.getPrecision(column);
   }


   /**
    * What's a column's number of digits to right of the decimal point?
    *
    * @param column the first column is 1, the second is 2, ...
    * @return scale
    * @exception SQLException if a database-access error occurs.
    */
   public int getScale(int column) throws SQLException
   {
      return columnsInfo.getScale(column);
   }


   /**
    * What's a column's table's schema?
    *
    * @param column the first column is 1, the second is 2, ...
    * @return schema name or "" if not applicable
    * @exception SQLException if a database-access error occurs.
    */
   public String getSchemaName(int column) throws SQLException
   {
      return "";
   }


   /**
    * What's a column's table name?
    *
    * @return table name or "" if not applicable
    * @exception SQLException if a database-access error occurs.
    */
   public String getTableName(int column) throws SQLException
   {
      String res = columnsInfo.getTableName(column);
      return res==null ? "" : res;
   }


   /**
    * Is the column automatically numbered, thus read-only?
    *
    * @param column the first column is 1, the second is 2, ...
    * @return true if so
    * @exception SQLException if a database-access error occurs.
    */
   public boolean isAutoIncrement(int column) throws SQLException
   {
      return columnsInfo.isAutoIncrement(column).booleanValue();
   }


   /**
    * Does a column's case matter?
    *
    * @param column the first column is 1, the second is 2, ...
    * @return true if so
    * @exception SQLException if a database-access error occurs.
    */
   public boolean isCaseSensitive(int column) throws SQLException
   {
      return columnsInfo.isCaseSensitive(column).booleanValue();
   }


   /**
    * Is the column a cash value?
    *
    * @param column the first column is 1, the second is 2, ...
    * @return true if so
    * @exception SQLException if a database-access error occurs.
    */
   public boolean isCurrency(int column) throws SQLException
   {
      switch (columnsInfo.getNativeType(column))
      {
         case Tds.SYBMONEYN:
         case Tds.SYBDATETIMN:
         case Tds.SYBMONEY4:
         {
            return true;
         }
         default:
         {
            return false;
         }
      }
   }


   /**
    * Will a write on the column definitely succeed?
    *
    * @param column the first column is 1, the second is 2, ...
    * @return true if so
    * @exception SQLException if a database-access error occurs.
    */
   public boolean isDefinitelyWritable(int column) throws SQLException
   {
      return false;
   }


   /**
    * Can you put a NULL in this column?
    *
    * @param column the first column is 1, the second is 2, ...
    * @return columnNoNulls, columnNullable or columnNullableUnknown
    * @exception SQLException if a database-access error occurs.
    */
   public int isNullable(int column) throws SQLException
   {
      return columnsInfo.isNullable(column);
   }


   /**
    * Is a column definitely not writable?
    *
    * @param column the first column is 1, the second is 2, ...
    * @return true if so
    * @exception SQLException if a database-access error occurs.
    */
   public boolean isReadOnly(int column) throws SQLException
   {
      return columnsInfo.isReadOnly(column).booleanValue();
   }


   /**
    * Can the column be used in a where clause?
    *
    * @param column the first column is 1, the second is 2, ...
    * @return true if so
    * @exception SQLException if a database-access error occurs.
    */
   public boolean isSearchable(int column) throws SQLException
   {
      return columnsInfo.getNativeType(column) != Tds.SYBIMAGE;
   }


   /**
    * Is the column a signed number?
    *
    * @param column the first column is 1, the second is 2, ...
    * @return true if so
    * @exception SQLException if a database-access error occurs.
    */
   public boolean isSigned(int column) throws SQLException
   {
      switch( columnsInfo.getNativeType(column) )
      {
         case Tds.SYBDECIMAL:
         case Tds.SYBNUMERIC:
         case Tds.SYBMONEYN:
         case Tds.SYBMONEY:
         case Tds.SYBMONEY4:
         case Tds.SYBSMALLMONEY:
         case Tds.SYBFLTN:
         case Tds.SYBFLT8:
         case Tds.SYBREAL:
         case Tds.SYBINT2:
         case Tds.SYBINT4:
            return true;

         case Tds.SYBBIT:
         case Tds.SYBBITN:
         case Tds.SYBNVARCHAR:
         case Tds.SYBVARCHAR:
         case Tds.SYBNCHAR:
         case Tds.SYBCHAR:
         case Tds.SYBBINARY:
         case Tds.SYBVARBINARY:
         case Tds.SYBDATETIMN:
         case Tds.SYBDATETIME:
         case Tds.SYBDATETIME4:
         case Tds.SYBUNIQUEID:
         case Tds.SYBINT1:
         case Tds.SYBIMAGE:
         case Tds.SYBTEXT:
         case Tds.SYBNTEXT:
            return false;

         case Tds.SYBINTN:
            return columnsInfo.getPrecision(column) > 1;

         default:
            throw new SQLException("Unknown column type.");
      }
   }


   /**
    * Is it possible for a write on the column to succeed?
    *
    * @param column the first column is 1, the second is 2, ...
    * @return true if so
    * @exception SQLException if a database-access error occurs.
    */
   public boolean isWritable(int column) throws SQLException
   {
      return !columnsInfo.isReadOnly(column).booleanValue();
   }

   /**
    * JDBC 2.0
    *
    * <p>Returns the fully-qualified name of the Java class whose instances
    * are manufactured if the method <code>ResultSet.getObject</code>
    * is called to retrieve a value
    * from the column.  <code>ResultSet.getObject</code> may return a subclass of the
    * class returned by this method.
    *
    * @return the fully-qualified name of the class in the Java programming
    *         language that would be used by the method
    * <code>ResultSet.getObject</code> to retrieve the value in the specified
    * column. This is the class name used for custom mapping.
    * @exception SQLException if a database access error occurs
    */
   public String getColumnClassName(int column) throws SQLException
   {
      NotImplemented();
      return null;
   }
}
