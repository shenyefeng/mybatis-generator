/**
 *    Copyright 2006-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package mbg.test.mb3.mixed.hierarchical.immutable;

import static mbg.test.common.util.TestUtilities.blobsAreEqual;
import static mbg.test.common.util.TestUtilities.generateRandomBlob;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Mapper.FieldsblobsMapper;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Mapper.FieldsonlyMapper;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Mapper.PkblobsMapper;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Mapper.PkfieldsMapper;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Mapper.PkfieldsblobsMapper;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Mapper.PkonlyMapper;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Model.Fieldsblobs;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Model.FieldsblobsCriteria;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Model.FieldsblobsWithBLOBs;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Model.Fieldsonly;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Model.FieldsonlyCriteria;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Model.PkblobsCriteria;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Model.PkblobsKey;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Model.PkblobsWithBLOBs;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Model.Pkfields;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Model.PkfieldsCriteria;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Model.Pkfieldsblobs;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Model.PkfieldsblobsCriteria;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Model.PkfieldsblobsWithBLOBs;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Model.PkonlyCriteria;
import mbg.test.mb3.generated.mixed.hierarchical.Immutable.Model.PkonlyKey;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class UpdateByCriteriaTest extends AbstractMixedHierarchicalImmutableTest {

    @Test
    public void testFieldsOnlyUpdateByCriteriaSelective() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            FieldsonlyMapper mapper = sqlSession
                    .getMapper(FieldsonlyMapper.class);
            Fieldsonly record = new Fieldsonly(5, 11.22, 33.44);
            mapper.insert(record);

            record = new Fieldsonly(8, 44.55, 66.77);
            mapper.insert(record);

            record = new Fieldsonly(9, 88.99, 100.111);
            mapper.insert(record);

            record = new Fieldsonly(null, 99d, null);
            FieldsonlyCriteria example = new FieldsonlyCriteria();
            example.createCriteria().andIntegerfieldGreaterThan(5);

            int rows = mapper.updateByCriteriaSelective(record, example);
            assertEquals(2, rows);

            example.clear();
            example.createCriteria().andIntegerfieldEqualTo(5);
            List<Fieldsonly> answer = mapper.selectByCriteria(example);
            assertEquals(1, answer.size());
            record = answer.get(0);
            assertEquals(record.getDoublefield(), 11.22, 0.0);
            assertEquals(record.getFloatfield(), 33.44, 0.0);
            assertEquals(record.getIntegerfield().intValue(), 5);

            example.clear();
            example.createCriteria().andIntegerfieldEqualTo(8);
            answer = mapper.selectByCriteria(example);
            assertEquals(1, answer.size());
            record = answer.get(0);
            assertEquals(record.getDoublefield(), 99d, 0.0);
            assertEquals(record.getFloatfield(), 66.77, 0.0);
            assertEquals(record.getIntegerfield().intValue(), 8);

            example.clear();
            example.createCriteria().andIntegerfieldEqualTo(9);
            answer = mapper.selectByCriteria(example);
            assertEquals(1, answer.size());
            record = answer.get(0);
            assertEquals(record.getDoublefield(), 99d, 0.0);
            assertEquals(record.getFloatfield(), 100.111, 0.0);
            assertEquals(record.getIntegerfield().intValue(), 9);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testFieldsOnlyUpdateByCriteria() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            FieldsonlyMapper mapper = sqlSession
                    .getMapper(FieldsonlyMapper.class);
            Fieldsonly record = new Fieldsonly(5, 11.22, 33.44);
            mapper.insert(record);

            record = new Fieldsonly(8, 44.55, 66.77);
            mapper.insert(record);

            record = new Fieldsonly(9, 88.99, 100.111);
            mapper.insert(record);

            record = new Fieldsonly(22, null, null);
            FieldsonlyCriteria example = new FieldsonlyCriteria();
            example.createCriteria().andIntegerfieldEqualTo(5);

            int rows = mapper.updateByCriteria(record, example);
            assertEquals(1, rows);

            example.clear();
            example.createCriteria().andIntegerfieldEqualTo(22);
            List<Fieldsonly> answer = mapper.selectByCriteria(example);
            assertEquals(1, answer.size());
            record = answer.get(0);
            assertNull(record.getDoublefield());
            assertNull(record.getFloatfield());
            assertEquals(record.getIntegerfield().intValue(), 22);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testPKOnlyUpdateByCriteriaSelective() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            PkonlyMapper mapper = sqlSession.getMapper(PkonlyMapper.class);
            PkonlyKey key = new PkonlyKey(1, 3);
            mapper.insert(key);

            key = new PkonlyKey(5, 6);
            mapper.insert(key);

            key = new PkonlyKey(7, 8);
            mapper.insert(key);

            PkonlyCriteria example = new PkonlyCriteria();
            example.createCriteria().andIdGreaterThan(4);
            key = new PkonlyKey(null, 3);
            int rows = mapper.updateByCriteriaSelective(key, example);
            assertEquals(2, rows);

            example.clear();
            example.createCriteria().andIdEqualTo(5).andSeqNumEqualTo(3);

            long returnedRows = mapper.countByCriteria(example);
            assertEquals(1, returnedRows);

            example.clear();
            example.createCriteria().andIdEqualTo(7).andSeqNumEqualTo(3);

            returnedRows = mapper.countByCriteria(example);
            assertEquals(1, returnedRows);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testPKOnlyUpdateByCriteria() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            PkonlyMapper mapper = sqlSession.getMapper(PkonlyMapper.class);
            PkonlyKey key = new PkonlyKey(1, 3);
            mapper.insert(key);

            key = new PkonlyKey(5, 6);
            mapper.insert(key);

            key = new PkonlyKey(7, 8);
            mapper.insert(key);

            PkonlyCriteria example = new PkonlyCriteria();
            example.createCriteria().andIdEqualTo(7);
            key = new PkonlyKey(22, 3);
            int rows = mapper.updateByCriteria(key, example);
            assertEquals(1, rows);

            example.clear();
            example.createCriteria().andIdEqualTo(22).andSeqNumEqualTo(3);

            long returnedRows = mapper.countByCriteria(example);
            assertEquals(1, returnedRows);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testPKFieldsUpdateByCriteriaSelective() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            PkfieldsMapper mapper = sqlSession.getMapper(PkfieldsMapper.class);
            Pkfields record = new Pkfields();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setId1(1);
            record.setId2(2);
            mapper.insert(record);

            record = new Pkfields();
            record.setFirstname("Bob");
            record.setLastname("Jones");
            record.setId1(3);
            record.setId2(4);

            mapper.insert(record);

            record = new Pkfields();
            record.setFirstname("Fred");
            PkfieldsCriteria example = new PkfieldsCriteria();
            example.createCriteria().andLastnameLike("J%");
            int rows = mapper.updateByCriteriaSelective(record, example);
            assertEquals(1, rows);

            example.clear();
            example.createCriteria().andFirstnameEqualTo("Fred")
                    .andLastnameEqualTo("Jones").andId1EqualTo(3)
                    .andId2EqualTo(4);

            long returnedRows = mapper.countByCriteria(example);
            assertEquals(1, returnedRows);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testPKFieldsUpdateByCriteria() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            PkfieldsMapper mapper = sqlSession.getMapper(PkfieldsMapper.class);
            Pkfields record = new Pkfields();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setId1(1);
            record.setId2(2);
            mapper.insert(record);

            record = new Pkfields();
            record.setFirstname("Bob");
            record.setLastname("Jones");
            record.setId1(3);
            record.setId2(4);

            mapper.insert(record);

            record = new Pkfields();
            record.setFirstname("Fred");
            record.setId1(3);
            record.setId2(4);
            PkfieldsCriteria example = new PkfieldsCriteria();
            example.createCriteria().andId1EqualTo(3).andId2EqualTo(4);

            int rows = mapper.updateByCriteria(record, example);
            assertEquals(1, rows);

            example.clear();
            example.createCriteria().andFirstnameEqualTo("Fred")
                    .andLastnameIsNull().andId1EqualTo(3).andId2EqualTo(4);

            long returnedRows = mapper.countByCriteria(example);
            assertEquals(1, returnedRows);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testPKBlobsUpdateByCriteriaSelective() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            PkblobsMapper mapper = sqlSession.getMapper(PkblobsMapper.class);
            PkblobsWithBLOBs record = new PkblobsWithBLOBs(3,
                    generateRandomBlob(), generateRandomBlob(), "Long String 1");
            mapper.insert(record);

            record = new PkblobsWithBLOBs(6, generateRandomBlob(),
                    generateRandomBlob(), "Long String 2");
            mapper.insert(record);

            PkblobsWithBLOBs newRecord = new PkblobsWithBLOBs(null,
                    generateRandomBlob(), null, null);

            PkblobsCriteria example = new PkblobsCriteria();
            example.createCriteria().andIdGreaterThan(4);
            int rows = mapper.updateByCriteriaSelective(newRecord, example);
            assertEquals(1, rows);

            List<PkblobsWithBLOBs> answer = mapper
                    .selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());

            PkblobsWithBLOBs returnedRecord = answer.get(0);

            assertEquals(6, returnedRecord.getId().intValue());
            assertTrue(blobsAreEqual(newRecord.getBlob1(),
                    returnedRecord.getBlob1()));
            assertTrue(blobsAreEqual(record.getBlob2(),
                    returnedRecord.getBlob2()));
            assertEquals(record.getCharacterlob(), returnedRecord.getCharacterlob());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testPKBlobsUpdateByCriteriaWithoutBLOBs() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            PkblobsMapper mapper = sqlSession.getMapper(PkblobsMapper.class);
            PkblobsWithBLOBs record = new PkblobsWithBLOBs(3,
                    generateRandomBlob(), generateRandomBlob(), "Long String 1");
            mapper.insert(record);

            record = new PkblobsWithBLOBs(6, generateRandomBlob(),
                    generateRandomBlob(), "Long String 2");
            mapper.insert(record);

            PkblobsKey newRecord = new PkblobsKey(8);

            PkblobsCriteria example = new PkblobsCriteria();
            example.createCriteria().andIdGreaterThan(4);
            int rows = mapper.updateByCriteria(newRecord, example);
            assertEquals(1, rows);

            List<PkblobsWithBLOBs> answer = mapper
                    .selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());

            PkblobsWithBLOBs returnedRecord = answer.get(0);

            assertEquals(8, returnedRecord.getId().intValue());
            assertTrue(blobsAreEqual(record.getBlob1(),
                    returnedRecord.getBlob1()));
            assertTrue(blobsAreEqual(record.getBlob2(),
                    returnedRecord.getBlob2()));
            assertEquals(record.getCharacterlob(), returnedRecord.getCharacterlob());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testPKBlobsUpdateByCriteriaWithBLOBs() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            PkblobsMapper mapper = sqlSession.getMapper(PkblobsMapper.class);
            PkblobsWithBLOBs record = new PkblobsWithBLOBs(3,
                    generateRandomBlob(), generateRandomBlob(), "Long String 1");
            mapper.insert(record);

            record = new PkblobsWithBLOBs(6, generateRandomBlob(),
                    generateRandomBlob(), "Long String 2");
            mapper.insert(record);

            PkblobsWithBLOBs newRecord = new PkblobsWithBLOBs(8, null, null, null);

            PkblobsCriteria example = new PkblobsCriteria();
            example.createCriteria().andIdGreaterThan(4);
            int rows = mapper.updateByCriteriaWithBLOBs(newRecord, example);
            assertEquals(1, rows);

            List<PkblobsWithBLOBs> answer = mapper
                    .selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());

            PkblobsWithBLOBs returnedRecord = answer.get(0);

            assertEquals(8, returnedRecord.getId().intValue());
            assertNull(returnedRecord.getBlob1());
            assertNull(returnedRecord.getBlob2());
            assertNull(returnedRecord.getCharacterlob());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testPKFieldsBlobsUpdateByCriteriaSelective() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            PkfieldsblobsMapper mapper = sqlSession
                    .getMapper(PkfieldsblobsMapper.class);
            PkfieldsblobsWithBLOBs record = new PkfieldsblobsWithBLOBs(3, 4,
                    "Jeff", "Smith", generateRandomBlob());
            mapper.insert(record);

            record = new PkfieldsblobsWithBLOBs(5, 6, "Scott", "Jones",
                    generateRandomBlob());
            mapper.insert(record);

            PkfieldsblobsWithBLOBs newRecord = new PkfieldsblobsWithBLOBs(null,
                    null, "Fred", null, null);
            PkfieldsblobsCriteria example = new PkfieldsblobsCriteria();
            example.createCriteria().andId1NotEqualTo(3);
            int rows = mapper.updateByCriteriaSelective(newRecord, example);
            assertEquals(1, rows);

            List<PkfieldsblobsWithBLOBs> answer = mapper
                    .selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());

            PkfieldsblobsWithBLOBs returnedRecord = answer.get(0);

            assertEquals(record.getId1(), returnedRecord.getId1());
            assertEquals(record.getId2(), returnedRecord.getId2());
            assertEquals(newRecord.getFirstname(),
                    returnedRecord.getFirstname());
            assertEquals(record.getLastname(), returnedRecord.getLastname());
            assertTrue(blobsAreEqual(record.getBlob1(),
                    returnedRecord.getBlob1()));

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testPKFieldsBlobsUpdateByCriteriaWithoutBLOBs() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            PkfieldsblobsMapper mapper = sqlSession
                    .getMapper(PkfieldsblobsMapper.class);
            PkfieldsblobsWithBLOBs record = new PkfieldsblobsWithBLOBs(3, 4,
                    "Jeff", "Smith", generateRandomBlob());
            mapper.insert(record);

            record = new PkfieldsblobsWithBLOBs(5, 6, "Scott", "Jones",
                    generateRandomBlob());
            mapper.insert(record);

            Pkfieldsblobs newRecord = new Pkfieldsblobs(5, 8, "Fred", null);
            PkfieldsblobsCriteria example = new PkfieldsblobsCriteria();
            example.createCriteria().andId1EqualTo(5);
            int rows = mapper.updateByCriteria(newRecord, example);
            assertEquals(1, rows);

            List<PkfieldsblobsWithBLOBs> answer = mapper
                    .selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());

            PkfieldsblobsWithBLOBs returnedRecord = answer.get(0);

            assertEquals(newRecord.getId1(), returnedRecord.getId1());
            assertEquals(newRecord.getId2(), returnedRecord.getId2());
            assertEquals(newRecord.getFirstname(),
                    returnedRecord.getFirstname());
            assertNull(returnedRecord.getLastname());
            assertTrue(blobsAreEqual(record.getBlob1(),
                    returnedRecord.getBlob1()));

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testPKFieldsBlobsUpdateByCriteriaWithBLOBs() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            PkfieldsblobsMapper mapper = sqlSession
                    .getMapper(PkfieldsblobsMapper.class);
            PkfieldsblobsWithBLOBs record = new PkfieldsblobsWithBLOBs(3, 4,
                    "Jeff", "Smith", generateRandomBlob());
            mapper.insert(record);

            record = new PkfieldsblobsWithBLOBs(5, 6, "Scott", "Jones",
                    generateRandomBlob());
            mapper.insert(record);

            PkfieldsblobsWithBLOBs newRecord = new PkfieldsblobsWithBLOBs(3, 8,
                    "Fred", null, null);
            PkfieldsblobsCriteria example = new PkfieldsblobsCriteria();
            example.createCriteria().andId1EqualTo(3);
            int rows = mapper.updateByCriteriaWithBLOBs(newRecord, example);
            assertEquals(1, rows);

            List<PkfieldsblobsWithBLOBs> answer = mapper
                    .selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());

            PkfieldsblobsWithBLOBs returnedRecord = answer.get(0);

            assertEquals(newRecord.getId1(), returnedRecord.getId1());
            assertEquals(newRecord.getId2(), returnedRecord.getId2());
            assertEquals(newRecord.getFirstname(),
                    returnedRecord.getFirstname());
            assertNull(returnedRecord.getLastname());
            assertNull(returnedRecord.getBlob1());

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testFieldsBlobsUpdateByCriteriaSelective() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            FieldsblobsMapper mapper = sqlSession
                    .getMapper(FieldsblobsMapper.class);
            FieldsblobsWithBLOBs record = new FieldsblobsWithBLOBs("Jeff",
                    "Smith", generateRandomBlob(), generateRandomBlob(), null);
            mapper.insert(record);

            record = new FieldsblobsWithBLOBs("Scott", "Jones",
                    generateRandomBlob(), generateRandomBlob(), null);
            mapper.insert(record);

            FieldsblobsWithBLOBs newRecord = new FieldsblobsWithBLOBs(null,
                    "Doe", null, null, null);
            FieldsblobsCriteria example = new FieldsblobsCriteria();
            example.createCriteria().andFirstnameLike("S%");
            int rows = mapper.updateByCriteriaSelective(newRecord, example);
            assertEquals(1, rows);

            List<FieldsblobsWithBLOBs> answer = mapper
                    .selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());

            FieldsblobsWithBLOBs returnedRecord = answer.get(0);

            assertEquals(record.getFirstname(), returnedRecord.getFirstname());
            assertEquals(newRecord.getLastname(), returnedRecord.getLastname());
            assertTrue(blobsAreEqual(record.getBlob1(),
                    returnedRecord.getBlob1()));
            assertTrue(blobsAreEqual(record.getBlob2(),
                    returnedRecord.getBlob2()));
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testFieldsBlobsUpdateByCriteriaWithoutBLOBs() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            FieldsblobsMapper mapper = sqlSession
                    .getMapper(FieldsblobsMapper.class);
            FieldsblobsWithBLOBs record = new FieldsblobsWithBLOBs("Jeff",
                    "Smith", generateRandomBlob(), generateRandomBlob(), null);
            mapper.insert(record);

            record = new FieldsblobsWithBLOBs("Scott", "Jones",
                    generateRandomBlob(), generateRandomBlob(), null);
            mapper.insert(record);

            Fieldsblobs newRecord = new Fieldsblobs("Scott", "Doe");
            FieldsblobsCriteria example = new FieldsblobsCriteria();
            example.createCriteria().andFirstnameLike("S%");
            int rows = mapper.updateByCriteria(newRecord, example);
            assertEquals(1, rows);

            List<FieldsblobsWithBLOBs> answer = mapper
                    .selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());

            FieldsblobsWithBLOBs returnedRecord = answer.get(0);

            assertEquals(newRecord.getFirstname(),
                    returnedRecord.getFirstname());
            assertEquals(newRecord.getLastname(), returnedRecord.getLastname());
            assertTrue(blobsAreEqual(record.getBlob1(),
                    returnedRecord.getBlob1()));
            assertTrue(blobsAreEqual(record.getBlob2(),
                    returnedRecord.getBlob2()));
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testFieldsBlobsUpdateByCriteriaWithBLOBs() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            FieldsblobsMapper mapper = sqlSession
                    .getMapper(FieldsblobsMapper.class);
            FieldsblobsWithBLOBs record = new FieldsblobsWithBLOBs("Jeff",
                    "Smith", generateRandomBlob(), generateRandomBlob(), null);
            mapper.insert(record);

            record = new FieldsblobsWithBLOBs("Scott", "Jones",
                    generateRandomBlob(), generateRandomBlob(), null);
            mapper.insert(record);

            FieldsblobsWithBLOBs newRecord = new FieldsblobsWithBLOBs("Scott",
                    "Doe", null, null, null);
            FieldsblobsCriteria example = new FieldsblobsCriteria();
            example.createCriteria().andFirstnameLike("S%");
            int rows = mapper.updateByCriteriaWithBLOBs(newRecord, example);
            assertEquals(1, rows);

            List<FieldsblobsWithBLOBs> answer = mapper
                    .selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());

            FieldsblobsWithBLOBs returnedRecord = answer.get(0);

            assertEquals(newRecord.getFirstname(),
                    returnedRecord.getFirstname());
            assertEquals(newRecord.getLastname(), returnedRecord.getLastname());
            assertNull(returnedRecord.getBlob1());
            assertNull(returnedRecord.getBlob2());
        } finally {
            sqlSession.close();
        }
    }
}
