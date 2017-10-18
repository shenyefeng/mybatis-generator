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
package mbg.test.ib2j5.hierarchical;

import static mbg.test.common.util.TestUtilities.blobsAreEqual;
import static mbg.test.common.util.TestUtilities.generateRandomBlob;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import mbg.test.ib2j5.generated.hierarchical.dao.AwfulTableDAO;
import mbg.test.ib2j5.generated.hierarchical.dao.FieldsblobsDAO;
import mbg.test.ib2j5.generated.hierarchical.dao.FieldsonlyDAO;
import mbg.test.ib2j5.generated.hierarchical.dao.PkblobsDAO;
import mbg.test.ib2j5.generated.hierarchical.dao.PkfieldsDAO;
import mbg.test.ib2j5.generated.hierarchical.dao.PkfieldsblobsDAO;
import mbg.test.ib2j5.generated.hierarchical.dao.PkonlyDAO;
import mbg.test.ib2j5.generated.hierarchical.model.AwfulTable;
import mbg.test.ib2j5.generated.hierarchical.model.AwfulTableCriteria;
import mbg.test.ib2j5.generated.hierarchical.model.Fieldsblobs;
import mbg.test.ib2j5.generated.hierarchical.model.FieldsblobsCriteria;
import mbg.test.ib2j5.generated.hierarchical.model.FieldsblobsWithBLOBs;
import mbg.test.ib2j5.generated.hierarchical.model.PkblobsCriteria;
import mbg.test.ib2j5.generated.hierarchical.model.PkblobsKey;
import mbg.test.ib2j5.generated.hierarchical.model.PkblobsWithBLOBs;
import mbg.test.ib2j5.generated.hierarchical.model.Pkfields;
import mbg.test.ib2j5.generated.hierarchical.model.PkfieldsCriteria;
import mbg.test.ib2j5.generated.hierarchical.model.Pkfieldsblobs;
import mbg.test.ib2j5.generated.hierarchical.model.PkfieldsblobsCriteria;
import mbg.test.ib2j5.generated.hierarchical.model.PkfieldsblobsWithBLOBs;
import mbg.test.ib2j5.generated.hierarchical.model.PkonlyCriteria;
import mbg.test.ib2j5.generated.hierarchical.model.PkonlyKey;
import mbg.test.ib2j5.generated.hierarchical.model.subpackage.Fieldsonly;
import mbg.test.ib2j5.generated.hierarchical.model.subpackage.FieldsonlyCriteria;

/**
 * 
 * @author Jeff Butler
 *
 */
public class UpdateByCriteriaTest extends AbstractHierarchicalJava5Test {

    @Test
    public void testFieldsOnlyUpdateByCriteriaSelective() {
        FieldsonlyDAO dao = getFieldsonlyDAO();

        try {
            Fieldsonly record = new Fieldsonly();
            record.setDoublefield(11.22);
            record.setFloatfield(33.44);
            record.setIntegerfield(5);
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(44.55);
            record.setFloatfield(66.77);
            record.setIntegerfield(8);
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(88.99);
            record.setFloatfield(100.111);
            record.setIntegerfield(9);
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(99d);
            FieldsonlyCriteria example = new FieldsonlyCriteria();
            example.createCriteria().andIntegerfieldGreaterThan(5);
            
            int rows = dao.updateByCriteriaSelective(record, example);
            assertEquals(2, rows);

            example.clear();
            example.createCriteria().andIntegerfieldEqualTo(5);
            List<Fieldsonly> answer = dao.selectByCriteria(example);
            assertEquals(1, answer.size());
            record = answer.get(0);
            assertEquals(record.getDoublefield(), 11.22, 0.001);
            assertEquals(record.getFloatfield(), 33.44, 0.001);
            assertEquals(record.getIntegerfield().intValue(), 5);
            
            example.clear();
            example.createCriteria().andIntegerfieldEqualTo(8);
            answer = dao.selectByCriteria(example);
            assertEquals(1, answer.size());
            record = answer.get(0);
            assertEquals(record.getDoublefield(), 99, 0.001);
            assertEquals(record.getFloatfield(), 66.77, 0.001);
            assertEquals(record.getIntegerfield().intValue(), 8);
            
            example.clear();
            example.createCriteria().andIntegerfieldEqualTo(9);
            answer = dao.selectByCriteria(example);
            assertEquals(1, answer.size());
            record = answer.get(0);
            assertEquals(record.getDoublefield(), 99, 0.001);
            assertEquals(record.getFloatfield(), 100.111, 0.001);
            assertEquals(record.getIntegerfield().intValue(), 9);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testFieldsOnlyUpdateByCriteria() {
        FieldsonlyDAO dao = getFieldsonlyDAO();

        try {
            Fieldsonly record = new Fieldsonly();
            record.setDoublefield(11.22);
            record.setFloatfield(33.44);
            record.setIntegerfield(5);
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(44.55);
            record.setFloatfield(66.77);
            record.setIntegerfield(8);
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(88.99);
            record.setFloatfield(100.111);
            record.setIntegerfield(9);
            dao.insert(record);

            record = new Fieldsonly();
            record.setIntegerfield(22);
            FieldsonlyCriteria example = new FieldsonlyCriteria();
            example.createCriteria().andIntegerfieldEqualTo(5);
            
            int rows = dao.updateByCriteria(record, example);
            assertEquals(1, rows);

            example.clear();
            example.createCriteria().andIntegerfieldEqualTo(22);
            List<Fieldsonly> answer = dao.selectByCriteria(example);
            assertEquals(1, answer.size());
            record = answer.get(0);
            assertNull(record.getDoublefield());
            assertNull(record.getFloatfield());
            assertEquals(record.getIntegerfield().intValue(), 22);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKOnlyUpdateByCriteriaSelective() {
        PkonlyDAO dao = getPkonlyDAO();

        try {
            PkonlyKey key = new PkonlyKey();
            key.setId(1);
            key.setSeqNum(3);
            dao.insert(key);

            key = new PkonlyKey();
            key.setId(5);
            key.setSeqNum(6);
            dao.insert(key);

            key = new PkonlyKey();
            key.setId(7);
            key.setSeqNum(8);
            dao.insert(key);

            PkonlyCriteria example = new PkonlyCriteria();
            example.createCriteria().andIdGreaterThan(4);
            key = new PkonlyKey();
            key.setSeqNum(3);
            int rows = dao.updateByCriteriaSelective(key, example);
            assertEquals(2, rows);

            example.clear();
            example.createCriteria()
                .andIdEqualTo(5)
                .andSeqNumEqualTo(3);
            
            long returnedRows = dao.countByCriteria(example);
            assertEquals(1, returnedRows);
            
            example.clear();
            example.createCriteria()
                .andIdEqualTo(7)
                .andSeqNumEqualTo(3);
            
            returnedRows = dao.countByCriteria(example);
            assertEquals(1, returnedRows);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKOnlyUpdateByCriteria() {
        PkonlyDAO dao = getPkonlyDAO();

        try {
            PkonlyKey key = new PkonlyKey();
            key.setId(1);
            key.setSeqNum(3);
            dao.insert(key);

            key = new PkonlyKey();
            key.setId(5);
            key.setSeqNum(6);
            dao.insert(key);

            key = new PkonlyKey();
            key.setId(7);
            key.setSeqNum(8);
            dao.insert(key);

            PkonlyCriteria example = new PkonlyCriteria();
            example.createCriteria()
                .andIdEqualTo(7);
            key = new PkonlyKey();
            key.setSeqNum(3);
            key.setId(22);
            int rows = dao.updateByCriteria(key, example);
            assertEquals(1, rows);

            example.clear();
            example.createCriteria()
                .andIdEqualTo(22)
                .andSeqNumEqualTo(3);
            
            long returnedRows = dao.countByCriteria(example);
            assertEquals(1, returnedRows);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsUpdateByCriteriaSelective() {
        PkfieldsDAO dao = getPkfieldsDAO();
    
        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setId1(1);
            record.setId2(2);
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Bob");
            record.setLastname("Jones");
            record.setId1(3);
            record.setId2(4);
    
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Fred");
            PkfieldsCriteria example = new PkfieldsCriteria();
            example.createCriteria().andLastnameLike("J%");
            int rows = dao.updateByCriteriaSelective(record, example);
            assertEquals(1, rows);
            
            example.clear();
            example.createCriteria()
                .andFirstnameEqualTo("Fred")
                .andLastnameEqualTo("Jones")
                .andId1EqualTo(3)
                .andId2EqualTo(4);
    
            long returnedRows = dao.countByCriteria(example);
            assertEquals(1, returnedRows);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsUpdateByCriteria() {
        PkfieldsDAO dao = getPkfieldsDAO();
    
        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setId1(1);
            record.setId2(2);
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Bob");
            record.setLastname("Jones");
            record.setId1(3);
            record.setId2(4);
    
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Fred");
            record.setId1(3);
            record.setId2(4);
            PkfieldsCriteria example = new PkfieldsCriteria();
            example.createCriteria()
                .andId1EqualTo(3)
                .andId2EqualTo(4);
            
            int rows = dao.updateByCriteria(record, example);
            assertEquals(1, rows);
            
            example.clear();
            example.createCriteria()
                .andFirstnameEqualTo("Fred")
                .andLastnameIsNull()
                .andId1EqualTo(3)
                .andId2EqualTo(4);
    
            long returnedRows = dao.countByCriteria(example);
            assertEquals(1, returnedRows);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKBlobsUpdateByCriteriaSelective() {
        PkblobsDAO dao = getPkblobsDAO();
    
        try {
            PkblobsWithBLOBs record = new PkblobsWithBLOBs();
            record.setId(3);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);
    
            record = new PkblobsWithBLOBs();
            record.setId(6);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);
    
            PkblobsWithBLOBs newRecord = new PkblobsWithBLOBs();
            newRecord.setBlob1(generateRandomBlob());
            
            PkblobsCriteria example = new PkblobsCriteria();
            example.createCriteria().andIdGreaterThan(4);
            int rows = dao.updateByCriteriaSelective(newRecord, example);
            assertEquals(1, rows);
            
            List<PkblobsWithBLOBs> answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
            
            PkblobsWithBLOBs returnedRecord = answer.get(0);
            
            assertEquals(6, returnedRecord.getId().intValue());
            assertTrue(blobsAreEqual(newRecord.getBlob1(), returnedRecord.getBlob1()));
            assertTrue(blobsAreEqual(record.getBlob2(), returnedRecord.getBlob2()));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKBlobsUpdateByCriteriaWithoutBLOBs() {
        PkblobsDAO dao = getPkblobsDAO();
    
        try {
            PkblobsWithBLOBs record = new PkblobsWithBLOBs();
            record.setId(3);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);
    
            record = new PkblobsWithBLOBs();
            record.setId(6);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);
    
            PkblobsKey newRecord = new PkblobsKey();
            newRecord.setId(8);
            
            PkblobsCriteria example = new PkblobsCriteria();
            example.createCriteria().andIdGreaterThan(4);
            int rows = dao.updateByCriteria(newRecord, example);
            assertEquals(1, rows);
            
            List<PkblobsWithBLOBs> answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
            
            PkblobsWithBLOBs returnedRecord = answer.get(0);
            
            assertEquals(8, returnedRecord.getId().intValue());
            assertTrue(blobsAreEqual(record.getBlob1(), returnedRecord.getBlob1()));
            assertTrue(blobsAreEqual(record.getBlob2(), returnedRecord.getBlob2()));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKBlobsUpdateByCriteriaWithBLOBs() {
        PkblobsDAO dao = getPkblobsDAO();
    
        try {
            PkblobsWithBLOBs record = new PkblobsWithBLOBs();
            record.setId(3);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);
    
            record = new PkblobsWithBLOBs();
            record.setId(6);
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);
    
            PkblobsWithBLOBs newRecord = new PkblobsWithBLOBs();
            newRecord.setId(8);
            
            PkblobsCriteria example = new PkblobsCriteria();
            example.createCriteria().andIdGreaterThan(4);
            int rows = dao.updateByCriteria(newRecord, example);
            assertEquals(1, rows);
            
            List<PkblobsWithBLOBs> answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
            
            PkblobsWithBLOBs returnedRecord = answer.get(0);
            
            assertEquals(8, returnedRecord.getId().intValue());
            assertNull(returnedRecord.getBlob1());
            assertNull(returnedRecord.getBlob2());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsBlobsUpdateByCriteriaSelective() {
        PkfieldsblobsDAO dao = getPkfieldsblobsDAO();
    
        try {
            PkfieldsblobsWithBLOBs record = new PkfieldsblobsWithBLOBs();
            record.setId1(3);
            record.setId2(4);
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);
    
            record = new PkfieldsblobsWithBLOBs();
            record.setId1(5);
            record.setId2(6);
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            PkfieldsblobsWithBLOBs newRecord = new PkfieldsblobsWithBLOBs();
            newRecord.setFirstname("Fred");
            PkfieldsblobsCriteria example = new PkfieldsblobsCriteria();
            example.createCriteria().andId1NotEqualTo(3);
            int rows = dao.updateByCriteriaSelective(newRecord, example);
            assertEquals(1, rows);
    
            List<PkfieldsblobsWithBLOBs> answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
            
            PkfieldsblobsWithBLOBs returnedRecord = answer.get(0);
            
            assertEquals(record.getId1(), returnedRecord.getId1());
            assertEquals(record.getId2(), returnedRecord.getId2());
            assertEquals(newRecord.getFirstname(), returnedRecord.getFirstname());
            assertEquals(record.getLastname(), returnedRecord.getLastname());
            assertTrue(blobsAreEqual(record.getBlob1(), returnedRecord.getBlob1()));
            
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsBlobsUpdateByCriteriaWithoutBLOBs() {
        PkfieldsblobsDAO dao = getPkfieldsblobsDAO();
    
        try {
            PkfieldsblobsWithBLOBs record = new PkfieldsblobsWithBLOBs();
            record.setId1(3);
            record.setId2(4);
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);
    
            record = new PkfieldsblobsWithBLOBs();
            record.setId1(5);
            record.setId2(6);
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            Pkfieldsblobs newRecord = new Pkfieldsblobs();
            newRecord.setId1(5);
            newRecord.setId2(8);
            newRecord.setFirstname("Fred");
            PkfieldsblobsCriteria example = new PkfieldsblobsCriteria();
            example.createCriteria().andId1EqualTo(5);
            int rows = dao.updateByCriteria(newRecord, example);
            assertEquals(1, rows);
    
            List<PkfieldsblobsWithBLOBs> answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
            
            PkfieldsblobsWithBLOBs returnedRecord = answer.get(0);
            
            assertEquals(newRecord.getId1(), returnedRecord.getId1());
            assertEquals(newRecord.getId2(), returnedRecord.getId2());
            assertEquals(newRecord.getFirstname(), returnedRecord.getFirstname());
            assertNull(returnedRecord.getLastname());
            assertTrue(blobsAreEqual(record.getBlob1(), returnedRecord.getBlob1()));
            
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testPKFieldsBlobsUpdateByCriteriaWithBLOBs() {
        PkfieldsblobsDAO dao = getPkfieldsblobsDAO();
    
        try {
            PkfieldsblobsWithBLOBs record = new PkfieldsblobsWithBLOBs();
            record.setId1(3);
            record.setId2(4);
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);
    
            record = new PkfieldsblobsWithBLOBs();
            record.setId1(5);
            record.setId2(6);
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(generateRandomBlob());
            dao.insert(record);

            PkfieldsblobsWithBLOBs newRecord = new PkfieldsblobsWithBLOBs();
            newRecord.setId1(3);
            newRecord.setId2(8);
            newRecord.setFirstname("Fred");
            PkfieldsblobsCriteria example = new PkfieldsblobsCriteria();
            example.createCriteria().andId1EqualTo(3);
            int rows = dao.updateByCriteria(newRecord, example);
            assertEquals(1, rows);
    
            List<PkfieldsblobsWithBLOBs> answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
            
            PkfieldsblobsWithBLOBs returnedRecord = answer.get(0);
            
            assertEquals(newRecord.getId1(), returnedRecord.getId1());
            assertEquals(newRecord.getId2(), returnedRecord.getId2());
            assertEquals(newRecord.getFirstname(), returnedRecord.getFirstname());
            assertNull(returnedRecord.getLastname());
            assertNull(returnedRecord.getBlob1());
            
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testFieldsBlobsUpdateByCriteriaSelective() {
        FieldsblobsDAO dao = getFieldsblobsDAO();
    
        try {
            FieldsblobsWithBLOBs record = new FieldsblobsWithBLOBs();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);
    
            record = new FieldsblobsWithBLOBs();
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            FieldsblobsWithBLOBs newRecord = new FieldsblobsWithBLOBs();
            newRecord.setLastname("Doe");
            FieldsblobsCriteria example = new FieldsblobsCriteria();
            example.createCriteria().andFirstnameLike("S%");
            int rows = dao.updateByCriteriaSelective(newRecord, example);
            assertEquals(1, rows);
            
            List<FieldsblobsWithBLOBs> answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
            
            FieldsblobsWithBLOBs returnedRecord = answer.get(0);
            
            assertEquals(record.getFirstname(), returnedRecord.getFirstname());
            assertEquals(newRecord.getLastname(), returnedRecord.getLastname());
            assertTrue(blobsAreEqual(record.getBlob1(), returnedRecord.getBlob1()));
            assertTrue(blobsAreEqual(record.getBlob2(), returnedRecord.getBlob2()));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testFieldsBlobsUpdateByCriteriaWithoutBLOBs() {
        FieldsblobsDAO dao = getFieldsblobsDAO();
    
        try {
            FieldsblobsWithBLOBs record = new FieldsblobsWithBLOBs();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);
    
            record = new FieldsblobsWithBLOBs();
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            Fieldsblobs newRecord = new Fieldsblobs();
            newRecord.setFirstname("Scott");
            newRecord.setLastname("Doe");
            FieldsblobsCriteria example = new FieldsblobsCriteria();
            example.createCriteria().andFirstnameLike("S%");
            int rows = dao.updateByCriteria(newRecord, example);
            assertEquals(1, rows);
            
            List<FieldsblobsWithBLOBs> answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
            
            FieldsblobsWithBLOBs returnedRecord = answer.get(0);
            
            assertEquals(newRecord.getFirstname(), returnedRecord.getFirstname());
            assertEquals(newRecord.getLastname(), returnedRecord.getLastname());
            assertTrue(blobsAreEqual(record.getBlob1(), returnedRecord.getBlob1()));
            assertTrue(blobsAreEqual(record.getBlob2(), returnedRecord.getBlob2()));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testFieldsBlobsUpdateByCriteriaWithBLOBs() {
        FieldsblobsDAO dao = getFieldsblobsDAO();
    
        try {
            FieldsblobsWithBLOBs record = new FieldsblobsWithBLOBs();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);
    
            record = new FieldsblobsWithBLOBs();
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(generateRandomBlob());
            record.setBlob2(generateRandomBlob());
            dao.insert(record);

            FieldsblobsWithBLOBs newRecord = new FieldsblobsWithBLOBs();
            newRecord.setFirstname("Scott");
            newRecord.setLastname("Doe");
            FieldsblobsCriteria example = new FieldsblobsCriteria();
            example.createCriteria().andFirstnameLike("S%");
            int rows = dao.updateByCriteria(newRecord, example);
            assertEquals(1, rows);
            
            List<FieldsblobsWithBLOBs> answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
            
            FieldsblobsWithBLOBs returnedRecord = answer.get(0);
            
            assertEquals(newRecord.getFirstname(), returnedRecord.getFirstname());
            assertEquals(newRecord.getLastname(), returnedRecord.getLastname());
            assertNull(returnedRecord.getBlob1());
            assertNull(returnedRecord.getBlob2());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAwfulTableUpdateByCriteriaSelective() {
        AwfulTableDAO dao = getAwfulTableDAO();
    
        try {
            AwfulTable record = new AwfulTable();
            record.seteMail("fred@fred.com");
            record.setEmailaddress("alsofred@fred.com");
            record.setFirstFirstName("fred1");
            record.setFrom("from field");
            record.setId1(1);
            record.setId2(2);
            record.setId5(5);
            record.setId6(6);
            record.setId7(7);
            record.setSecondFirstName("fred2");
            record.setThirdFirstName("fred3");
    
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("fred2@fred.com");
            record.setEmailaddress("alsofred2@fred.com");
            record.setFirstFirstName("fred11");
            record.setFrom("from from field");
            record.setId1(11);
            record.setId2(22);
            record.setId5(55);
            record.setId6(66);
            record.setId7(77);
            record.setSecondFirstName("fred22");
            record.setThirdFirstName("fred33");
    
            dao.insert(record);
    
            AwfulTable newRecord = new AwfulTable();
            newRecord.setFirstFirstName("Alonzo");
            AwfulTableCriteria example = new AwfulTableCriteria();
            example.createCriteria().andEMailLike("fred2@%");
            int rows = dao.updateByCriteriaSelective(newRecord, example);
            assertEquals(1, rows);
    
            List<AwfulTable> answer = dao.selectByCriteria(example);
            assertEquals(1, answer.size());

            AwfulTable returnedRecord = answer.get(0);
            
            assertEquals(record.getCustomerId(), returnedRecord.getCustomerId());
            assertEquals(record.geteMail(), returnedRecord.geteMail());
            assertEquals(record.getEmailaddress(), returnedRecord.getEmailaddress());
            assertEquals(newRecord.getFirstFirstName(), returnedRecord.getFirstFirstName());
            assertEquals(record.getFrom(), returnedRecord.getFrom());
            assertEquals(record.getId1(), returnedRecord.getId1());
            assertEquals(record.getId2(), returnedRecord.getId2());
            assertEquals(record.getId5(), returnedRecord.getId5());
            assertEquals(record.getId6(), returnedRecord.getId6());
            assertEquals(record.getId7(), returnedRecord.getId7());
            assertEquals(record.getSecondFirstName(), returnedRecord.getSecondFirstName());
            assertEquals(record.getThirdFirstName(), returnedRecord.getThirdFirstName());
            
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAwfulTableUpdateByCriteria() {
        AwfulTableDAO dao = getAwfulTableDAO();
    
        try {
            AwfulTable record = new AwfulTable();
            record.seteMail("fred@fred.com");
            record.setEmailaddress("alsofred@fred.com");
            record.setFirstFirstName("fred1");
            record.setFrom("from field");
            record.setId1(1);
            record.setId2(2);
            record.setId5(5);
            record.setId6(6);
            record.setId7(7);
            record.setSecondFirstName("fred2");
            record.setThirdFirstName("fred3");
    
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("fred2@fred.com");
            record.setEmailaddress("alsofred2@fred.com");
            record.setFirstFirstName("fred11");
            record.setFrom("from from field");
            record.setId1(11);
            record.setId2(22);
            record.setId5(55);
            record.setId6(66);
            record.setId7(77);
            record.setSecondFirstName("fred22");
            record.setThirdFirstName("fred33");
    
            dao.insert(record);
    
            AwfulTable newRecord = new AwfulTable();
            newRecord.setFirstFirstName("Alonzo");
            newRecord.setCustomerId(58);
            newRecord.setId1(111);
            newRecord.setId2(222);
            newRecord.setId5(555);
            newRecord.setId6(666);
            newRecord.setId7(777);
            AwfulTableCriteria example = new AwfulTableCriteria();
            example.createCriteria().andEMailLike("fred2@%");
            int rows = dao.updateByCriteria(newRecord, example);
            assertEquals(1, rows);

            example.clear();
            example.createCriteria().andCustomerIdEqualTo(58);
            List<AwfulTable> answer = dao.selectByCriteria(example);
            assertEquals(1, answer.size());

            AwfulTable returnedRecord = answer.get(0);
            
            assertEquals(newRecord.getCustomerId(), returnedRecord.getCustomerId());
            assertNull(returnedRecord.geteMail());
            assertNull(returnedRecord.getEmailaddress());
            assertEquals(newRecord.getFirstFirstName(), returnedRecord.getFirstFirstName());
            assertNull(returnedRecord.getFrom());
            assertEquals(newRecord.getId1(), returnedRecord.getId1());
            assertEquals(newRecord.getId2(), returnedRecord.getId2());
            assertEquals(newRecord.getId5(), returnedRecord.getId5());
            assertEquals(newRecord.getId6(), returnedRecord.getId6());
            assertEquals(newRecord.getId7(), returnedRecord.getId7());
            assertNull(returnedRecord.getSecondFirstName());
            assertNull(returnedRecord.getThirdFirstName());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
