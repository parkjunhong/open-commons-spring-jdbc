/*
 * Copyright 2021 Park Jun-Hong_(parkjunhong77@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 *
 * This file is generated under this project, "open-commons-spring-jdbc".
 *
 * Date  : 2021. 12. 24. 오후 1:10:57
 *
 * Author: parkjunhong77@gmail.com
 * 
 */

package open.commons.spring.jdbc.repository.mariadb;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import open.commons.spring.jdbc.repository.AbstractSingleDataSourceRepository;

/**
 * Mariadb 연동을 위한 클래스.
 * 
 * @since 2021. 12. 24.
 * @version 0.3.0
 * @author parkjunhong77@gmail.com
 */
public abstract class AbstractMariadbSingleDataSourceRepository<T> extends AbstractSingleDataSourceRepository<T> {

    protected final String QUERY_FOR_OFFSET = "LIMIT ?, ?";

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param entityType
     *            DBMS Table에 연결된 데이터 타입.
     *
     * @since 2021. 12. 24.
     * @version 0.3.0
     * @author parkjunhong77@gmail.com
     */
    public AbstractMariadbSingleDataSourceRepository(@NotNull Class<T> entityType) {
        super(entityType);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 12. 24.		박준홍			최초 작성
     * </pre>
     *
     * @param entityType
     *            DBMS Table에 연결된 데이터 타입.
     * @param forceToPrimitive
     *            Wrapper Class인 경우 Primitive 타입으로 강제로 변환할지 여부.
     *
     * @since 2021. 12. 24.
     * @version 0.3.0
     * @author parkjunhong77@gmail.com
     */
    public AbstractMariadbSingleDataSourceRepository(@NotNull Class<T> entityType, boolean forceToPrimitive) {
        super(entityType, forceToPrimitive);
    }

    /**
     *
     * @since 2021. 12. 24.
     * @version 0.3.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.jdbc.repository.AbstractGenericRepository#queryForOffset(int, int)
     */
    @Override
    protected String queryForOffset(@Min(0) int offset, @Min(1) int limit) {
        return QUERY_FOR_OFFSET;
    }

    /**
     *
     * @since 2021. 12. 24.
     * @version 0.3.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.jdbc.repository.AbstractGenericRepository#queryForPartitionConcatValue()
     */
    @Override
    protected String queryForPartitionConcatValue() {
        return ",";
    }

    /**
     *
     * @since 2021. 12. 24.
     * @version 0.3.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.jdbc.repository.AbstractGenericRepository#queryForPartitionHeader()
     */
    @Override
    protected String queryForPartitionHeader() {
        List<String> columns = getColumnNames();

        return new StringBuffer() //
                .append("INSERT INTO") //
                .append(" ") //
                .append(this.tableName) //
                .append(" (")//
                .append(String.join(", ", columns.toArray(new String[0]))) //
                .append(") ") //
                .append("VALUES") //
                .toString();
    }

    /**
     *
     * @since 2021. 12. 24.
     * @version 0.3.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.jdbc.repository.AbstractGenericRepository#queryForPartitionTail()
     */
    @Override
    protected String queryForPartitionTail() {
        return "";
    }

    /**
     *
     * @since 2021. 12. 24.
     * @version 0.3.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.spring.jdbc.repository.AbstractGenericRepository#queryForPartitionValue()
     */
    @Override
    protected String queryForPartitionValue() {
        return new StringBuilder().append("( ").append(queryForVariableBinding()).append(" )").toString();
    }
}