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
package mbg.test.ib2j2.flat;

import java.sql.SQLException;
import java.util.List;

import mbg.test.common.util.TestUtilities;
import mbg.test.ib2j2.generated.flat.dao.AwfulTableDAO;
import mbg.test.ib2j2.generated.flat.dao.FieldsblobsDAO;
import mbg.test.ib2j2.generated.flat.dao.FieldsonlyDAO;
import mbg.test.ib2j2.generated.flat.dao.PkblobsDAO;
import mbg.test.ib2j2.generated.flat.dao.PkfieldsDAO;
import mbg.test.ib2j2.generated.flat.dao.PkfieldsblobsDAO;
import mbg.test.ib2j2.generated.flat.dao.PkonlyDAO;
import mbg.test.ib2j2.generated.flat.model.AwfulTable;
import mbg.test.ib2j2.generated.flat.model.AwfulTableCriteria;
import mbg.test.ib2j2.generated.flat.model.Fieldsblobs;
import mbg.test.ib2j2.generated.flat.model.FieldsblobsCriteria;
import mbg.test.ib2j2.generated.flat.model.Fieldsonly;
import mbg.test.ib2j2.generated.flat.model.FieldsonlyCriteria;
import mbg.test.ib2j2.generated.flat.model.Pkblobs;
import mbg.test.ib2j2.generated.flat.model.PkblobsCriteria;
import mbg.test.ib2j2.generated.flat.model.Pkfields;
import mbg.test.ib2j2.generated.flat.model.PkfieldsCriteria;
import mbg.test.ib2j2.generated.flat.model.Pkfieldsblobs;
import mbg.test.ib2j2.generated.flat.model.PkfieldsblobsCriteria;
import mbg.test.ib2j2.generated.flat.model.Pkonly;
import mbg.test.ib2j2.generated.flat.model.PkonlyCriteria;

/**
 * 
 * @author Jeff Butler
 *
 */
public class UpdateByCriteriaTest extends AbstractFlatJava2Test {

    public void testFieldsOnlyUpdateByCriteriaSelective() {
        FieldsonlyDAO dao = getFieldsonlyDAO();

        try {
            Fieldsonly record = new Fieldsonly();
            record.setDoublefield(new Double(11.22));
            record.setFloatfield(new Double(33.44));
            record.setIntegerfield(new Integer(5));
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(new Double(44.55));
            record.setFloatfield(new Double(66.77));
            record.setIntegerfield(new Integer(8));
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(new Double(88.99));
            record.setFloatfield(new Double(100.111));
            record.setIntegerfield(new Integer(9));
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(new Double(99));
            FieldsonlyCriteria example = new FieldsonlyCriteria();
            example.createCriteria().andIntegerfieldGreaterThan(new Integer(5));
            
            int rows = dao.updateByCriteriaSelective(record, example);
            assertEquals(2, rows);

            example.clear();
            example.createCriteria().andIntegerfieldEqualTo(new Integer(5));
            List answer = dao.selectByCriteria(example);
            assertEquals(1, answer.size());
            record = (Fieldsonly) answer.get(0);
            assertEquals(record.getDoublefield(), new Double(11.22));
            assertEquals(record.getFloatfield(), new Double(33.44));
            assertEquals(record.getIntegerfield(), new Integer(5));
            
            example.clear();
            example.createCriteria().andIntegerfieldEqualTo(new Integer(8));
            answer = dao.selectByCriteria(example);
            assertEquals(1, answer.size());
            record = (Fieldsonly) answer.get(0);
            assertEquals(record.getDoublefield(), new Double(99));
            assertEquals(record.getFloatfield(), new Double(66.77));
            assertEquals(record.getIntegerfield(), new Integer(8));
            
            example.clear();
            example.createCriteria().andIntegerfieldEqualTo(new Integer(9));
            answer = dao.selectByCriteria(example);
            assertEquals(1, answer.size());
            record = (Fieldsonly) answer.get(0);
            assertEquals(record.getDoublefield(), new Double(99));
            assertEquals(record.getFloatfield(), new Double(100.111));
            assertEquals(record.getIntegerfield(), new Integer(9));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public void testFieldsOnlyUpdateByCriteria() {
        FieldsonlyDAO dao = getFieldsonlyDAO();

        try {
            Fieldsonly record = new Fieldsonly();
            record.setDoublefield(new Double(11.22));
            record.setFloatfield(new Double(33.44));
            record.setIntegerfield(new Integer(5));
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(new Double(44.55));
            record.setFloatfield(new Double(66.77));
            record.setIntegerfield(new Integer(8));
            dao.insert(record);

            record = new Fieldsonly();
            record.setDoublefield(new Double(88.99));
            record.setFloatfield(new Double(100.111));
            record.setIntegerfield(new Integer(9));
            dao.insert(record);

            record = new Fieldsonly();
            record.setIntegerfield(new Integer(22));
            FieldsonlyCriteria example = new FieldsonlyCriteria();
            example.createCriteria().andIntegerfieldEqualTo(new Integer(5));
            
            int rows = dao.updateByCriteria(record, example);
            assertEquals(1, rows);

            example.clear();
            example.createCriteria().andIntegerfieldEqualTo(new Integer(22));
            List answer = dao.selectByCriteria(example);
            assertEquals(1, answer.size());
            record = (Fieldsonly) answer.get(0);
            assertNull(record.getDoublefield());
            assertNull(record.getFloatfield());
            assertEquals(record.getIntegerfield(), new Integer(22));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public void testPKOnlyUpdateByCriteriaSelective() {
        PkonlyDAO dao = getPkonlyDAO();

        try {
            Pkonly key = new Pkonly();
            key.setId(new Integer(1));
            key.setSeqNum(new Integer(3));
            dao.insert(key);

            key = new Pkonly();
            key.setId(new Integer(5));
            key.setSeqNum(new Integer(6));
            dao.insert(key);

            key = new Pkonly();
            key.setId(new Integer(7));
            key.setSeqNum(new Integer(8));
            dao.insert(key);

            PkonlyCriteria example = new PkonlyCriteria();
            example.createCriteria().andIdGreaterThan(new Integer(4));
            key = new Pkonly();
            key.setSeqNum(new Integer(3));
            int rows = dao.updateByCriteriaSelective(key, example);
            assertEquals(2, rows);

            example.clear();
            example.createCriteria()
                .andIdEqualTo(new Integer(5))
                .andSeqNumEqualTo(new Integer(3));
            
            long returnedRows = dao.countByCriteria(example);
            assertEquals(1, returnedRows);
            
            example.clear();
            example.createCriteria()
                .andIdEqualTo(new Integer(7))
                .andSeqNumEqualTo(new Integer(3));
            
            returnedRows = dao.countByCriteria(example);
            assertEquals(1, returnedRows);
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public void testPKOnlyUpdateByCriteria() {
        PkonlyDAO dao = getPkonlyDAO();

        try {
            Pkonly key = new Pkonly();
            key.setId(new Integer(1));
            key.setSeqNum(new Integer(3));
            dao.insert(key);

            key = new Pkonly();
            key.setId(new Integer(5));
            key.setSeqNum(new Integer(6));
            dao.insert(key);

            key = new Pkonly();
            key.setId(new Integer(7));
            key.setSeqNum(new Integer(8));
            dao.insert(key);

            PkonlyCriteria example = new PkonlyCriteria();
            example.createCriteria()
                .andIdEqualTo(new Integer(7));
            key = new Pkonly();
            key.setSeqNum(new Integer(3));
            key.setId(new Integer(22));
            int rows = dao.updateByCriteria(key, example);
            assertEquals(1, rows);

            example.clear();
            example.createCriteria()
                .andIdEqualTo(new Integer(22))
                .andSeqNumEqualTo(new Integer(3));
            
            long returnedRows = dao.countByCriteria(example);
            assertEquals(1, returnedRows);
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public void testPKFieldsUpdateByCriteriaSelective() {
        PkfieldsDAO dao = getPkfieldsDAO();
    
        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setId1(new Integer(1));
            record.setId2(new Integer(2));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Bob");
            record.setLastname("Jones");
            record.setId1(new Integer(3));
            record.setId2(new Integer(4));
    
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
                .andId1EqualTo(new Integer(3))
                .andId2EqualTo(new Integer(4));
    
            long returnedRows = dao.countByCriteria(example);
            assertEquals(1, returnedRows);
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public void testPKFieldsUpdateByCriteria() {
        PkfieldsDAO dao = getPkfieldsDAO();
    
        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setId1(new Integer(1));
            record.setId2(new Integer(2));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Bob");
            record.setLastname("Jones");
            record.setId1(new Integer(3));
            record.setId2(new Integer(4));
    
            dao.insert(record);

            record = new Pkfields();
            record.setFirstname("Fred");
            record.setId1(new Integer(3));
            record.setId2(new Integer(4));
            PkfieldsCriteria example = new PkfieldsCriteria();
            example.createCriteria()
                .andId1EqualTo(new Integer(3))
                .andId2EqualTo(new Integer(4));
            
            int rows = dao.updateByCriteria(record, example);
            assertEquals(1, rows);
            
            example.clear();
            example.createCriteria()
                .andFirstnameEqualTo("Fred")
                .andLastnameIsNull()
                .andId1EqualTo(new Integer(3))
                .andId2EqualTo(new Integer(4));
    
            long returnedRows = dao.countByCriteria(example);
            assertEquals(1, returnedRows);
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public void testPKBlobsUpdateByCriteriaSelective() {
        PkblobsDAO dao = getPkblobsDAO();
    
        try {
            Pkblobs record = new Pkblobs();
            record.setId(new Integer(3));
            record.setBlob1(TestUtilities.generateRandomBlob());
            record.setBlob2(TestUtilities.generateRandomBlob());
            dao.insert(record);
    
            record = new Pkblobs();
            record.setId(new Integer(6));
            record.setBlob1(TestUtilities.generateRandomBlob());
            record.setBlob2(TestUtilities.generateRandomBlob());
            dao.insert(record);
    
            Pkblobs newRecord = new Pkblobs();
            newRecord.setBlob1(TestUtilities.generateRandomBlob());
            
            PkblobsCriteria example = new PkblobsCriteria();
            example.createCriteria().andIdGreaterThan(new Integer(4));
            int rows = dao.updateByCriteriaSelective(newRecord, example);
            assertEquals(1, rows);
            
            List answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
            
            Pkblobs returnedRecord = (Pkblobs) answer.get(0);
            
            assertEquals(new Integer(6), returnedRecord.getId());
            assertTrue(TestUtilities.blobsAreEqual(newRecord.getBlob1(), returnedRecord.getBlob1()));
            assertTrue(TestUtilities.blobsAreEqual(record.getBlob2(), returnedRecord.getBlob2()));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public void testPKBlobsUpdateByCriteriaWithoutBLOBs() {
        PkblobsDAO dao = getPkblobsDAO();
    
        try {
            Pkblobs record = new Pkblobs();
            record.setId(new Integer(3));
            record.setBlob1(TestUtilities.generateRandomBlob());
            record.setBlob2(TestUtilities.generateRandomBlob());
            dao.insert(record);
    
            record = new Pkblobs();
            record.setId(new Integer(6));
            record.setBlob1(TestUtilities.generateRandomBlob());
            record.setBlob2(TestUtilities.generateRandomBlob());
            dao.insert(record);
    
            Pkblobs newRecord = new Pkblobs();
            newRecord.setId(new Integer(8));
            
            PkblobsCriteria example = new PkblobsCriteria();
            example.createCriteria().andIdGreaterThan(new Integer(4));
            int rows = dao.updateByCriteriaWithoutBLOBs(newRecord, example);
            assertEquals(1, rows);
            
            List answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
            
            Pkblobs returnedRecord = (Pkblobs) answer.get(0);
            
            assertEquals(new Integer(8), returnedRecord.getId());
            assertTrue(TestUtilities.blobsAreEqual(record.getBlob1(), returnedRecord.getBlob1()));
            assertTrue(TestUtilities.blobsAreEqual(record.getBlob2(), returnedRecord.getBlob2()));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public void testPKBlobsUpdateByCriteriaWithBLOBs() {
        PkblobsDAO dao = getPkblobsDAO();
    
        try {
            Pkblobs record = new Pkblobs();
            record.setId(new Integer(3));
            record.setBlob1(TestUtilities.generateRandomBlob());
            record.setBlob2(TestUtilities.generateRandomBlob());
            dao.insert(record);
    
            record = new Pkblobs();
            record.setId(new Integer(6));
            record.setBlob1(TestUtilities.generateRandomBlob());
            record.setBlob2(TestUtilities.generateRandomBlob());
            dao.insert(record);
    
            Pkblobs newRecord = new Pkblobs();
            newRecord.setId(new Integer(8));
            
            PkblobsCriteria example = new PkblobsCriteria();
            example.createCriteria().andIdGreaterThan(new Integer(4));
            int rows = dao.updateByCriteriaWithBLOBs(newRecord, example);
            assertEquals(1, rows);
            
            List answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
            
            Pkblobs returnedRecord = (Pkblobs) answer.get(0);
            
            assertEquals(new Integer(8), returnedRecord.getId());
            assertNull(returnedRecord.getBlob1());
            assertNull(returnedRecord.getBlob2());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public void testPKFieldsBlobsUpdateByCriteriaSelective() {
        PkfieldsblobsDAO dao = getPkfieldsblobsDAO();
    
        try {
            Pkfieldsblobs record = new Pkfieldsblobs();
            record.setId1(new Integer(3));
            record.setId2(new Integer(4));
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(TestUtilities.generateRandomBlob());
            dao.insert(record);
    
            record = new Pkfieldsblobs();
            record.setId1(new Integer(5));
            record.setId2(new Integer(6));
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(TestUtilities.generateRandomBlob());
            dao.insert(record);

            Pkfieldsblobs newRecord = new Pkfieldsblobs();
            newRecord.setFirstname("Fred");
            PkfieldsblobsCriteria example = new PkfieldsblobsCriteria();
            example.createCriteria().andId1NotEqualTo(new Integer(3));
            int rows = dao.updateByCriteriaSelective(newRecord, example);
            assertEquals(1, rows);
    
            List answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
            
            Pkfieldsblobs returnedRecord = (Pkfieldsblobs) answer.get(0);
            
            assertEquals(record.getId1(), returnedRecord.getId1());
            assertEquals(record.getId2(), returnedRecord.getId2());
            assertEquals(newRecord.getFirstname(), returnedRecord.getFirstname());
            assertEquals(record.getLastname(), returnedRecord.getLastname());
            assertTrue(TestUtilities.blobsAreEqual(record.getBlob1(), returnedRecord.getBlob1()));
            
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public void testPKFieldsBlobsUpdateByCriteriaWithoutBLOBs() {
        PkfieldsblobsDAO dao = getPkfieldsblobsDAO();
    
        try {
            Pkfieldsblobs record = new Pkfieldsblobs();
            record.setId1(new Integer(3));
            record.setId2(new Integer(4));
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(TestUtilities.generateRandomBlob());
            dao.insert(record);
    
            record = new Pkfieldsblobs();
            record.setId1(new Integer(5));
            record.setId2(new Integer(6));
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(TestUtilities.generateRandomBlob());
            dao.insert(record);

            Pkfieldsblobs newRecord = new Pkfieldsblobs();
            newRecord.setId1(new Integer(5));
            newRecord.setId2(new Integer(8));
            newRecord.setFirstname("Fred");
            PkfieldsblobsCriteria example = new PkfieldsblobsCriteria();
            example.createCriteria().andId1EqualTo(new Integer(5));
            int rows = dao.updateByCriteriaWithoutBLOBs(newRecord, example);
            assertEquals(1, rows);
    
            List answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
            
            Pkfieldsblobs returnedRecord = (Pkfieldsblobs) answer.get(0);
            
            assertEquals(newRecord.getId1(), returnedRecord.getId1());
            assertEquals(newRecord.getId2(), returnedRecord.getId2());
            assertEquals(newRecord.getFirstname(), returnedRecord.getFirstname());
            assertNull(returnedRecord.getLastname());
            assertTrue(TestUtilities.blobsAreEqual(record.getBlob1(), returnedRecord.getBlob1()));
            
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public void testPKFieldsBlobsUpdateByCriteriaWithBLOBs() {
        PkfieldsblobsDAO dao = getPkfieldsblobsDAO();
    
        try {
            Pkfieldsblobs record = new Pkfieldsblobs();
            record.setId1(new Integer(3));
            record.setId2(new Integer(4));
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(TestUtilities.generateRandomBlob());
            dao.insert(record);
    
            record = new Pkfieldsblobs();
            record.setId1(new Integer(5));
            record.setId2(new Integer(6));
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(TestUtilities.generateRandomBlob());
            dao.insert(record);

            Pkfieldsblobs newRecord = new Pkfieldsblobs();
            newRecord.setId1(new Integer(3));
            newRecord.setId2(new Integer(8));
            newRecord.setFirstname("Fred");
            PkfieldsblobsCriteria example = new PkfieldsblobsCriteria();
            example.createCriteria().andId1EqualTo(new Integer(3));
            int rows = dao.updateByCriteriaWithBLOBs(newRecord, example);
            assertEquals(1, rows);
    
            List answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
            
            Pkfieldsblobs returnedRecord = (Pkfieldsblobs) answer.get(0);
            
            assertEquals(newRecord.getId1(), returnedRecord.getId1());
            assertEquals(newRecord.getId2(), returnedRecord.getId2());
            assertEquals(newRecord.getFirstname(), returnedRecord.getFirstname());
            assertNull(returnedRecord.getLastname());
            assertNull(returnedRecord.getBlob1());
            
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public void testFieldsBlobsUpdateByCriteriaSelective() {
        FieldsblobsDAO dao = getFieldsblobsDAO();
    
        try {
            Fieldsblobs record = new Fieldsblobs();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(TestUtilities.generateRandomBlob());
            record.setBlob2(TestUtilities.generateRandomBlob());
            dao.insert(record);
    
            record = new Fieldsblobs();
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(TestUtilities.generateRandomBlob());
            record.setBlob2(TestUtilities.generateRandomBlob());
            dao.insert(record);

            Fieldsblobs newRecord = new Fieldsblobs();
            newRecord.setLastname("Doe");
            FieldsblobsCriteria example = new FieldsblobsCriteria();
            example.createCriteria().andFirstnameLike("S%");
            int rows = dao.updateByCriteriaSelective(newRecord, example);
            assertEquals(1, rows);
            
            List answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
            
            Fieldsblobs returnedRecord = (Fieldsblobs) answer.get(0);
            
            assertEquals(record.getFirstname(), returnedRecord.getFirstname());
            assertEquals(newRecord.getLastname(), returnedRecord.getLastname());
            assertTrue(TestUtilities.blobsAreEqual(record.getBlob1(), returnedRecord.getBlob1()));
            assertTrue(TestUtilities.blobsAreEqual(record.getBlob2(), returnedRecord.getBlob2()));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public void testFieldsBlobsUpdateByCriteriaWithoutBLOBs() {
        FieldsblobsDAO dao = getFieldsblobsDAO();
    
        try {
            Fieldsblobs record = new Fieldsblobs();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(TestUtilities.generateRandomBlob());
            record.setBlob2(TestUtilities.generateRandomBlob());
            dao.insert(record);
    
            record = new Fieldsblobs();
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(TestUtilities.generateRandomBlob());
            record.setBlob2(TestUtilities.generateRandomBlob());
            dao.insert(record);

            Fieldsblobs newRecord = new Fieldsblobs();
            newRecord.setFirstname("Scott");
            newRecord.setLastname("Doe");
            FieldsblobsCriteria example = new FieldsblobsCriteria();
            example.createCriteria().andFirstnameLike("S%");
            int rows = dao.updateByCriteriaWithoutBLOBs(newRecord, example);
            assertEquals(1, rows);
            
            List answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
            
            Fieldsblobs returnedRecord = (Fieldsblobs) answer.get(0);
            
            assertEquals(newRecord.getFirstname(), returnedRecord.getFirstname());
            assertEquals(newRecord.getLastname(), returnedRecord.getLastname());
            assertTrue(TestUtilities.blobsAreEqual(record.getBlob1(), returnedRecord.getBlob1()));
            assertTrue(TestUtilities.blobsAreEqual(record.getBlob2(), returnedRecord.getBlob2()));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public void testFieldsBlobsUpdateByCriteriaWithBLOBs() {
        FieldsblobsDAO dao = getFieldsblobsDAO();
    
        try {
            Fieldsblobs record = new Fieldsblobs();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(TestUtilities.generateRandomBlob());
            record.setBlob2(TestUtilities.generateRandomBlob());
            dao.insert(record);
    
            record = new Fieldsblobs();
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(TestUtilities.generateRandomBlob());
            record.setBlob2(TestUtilities.generateRandomBlob());
            dao.insert(record);

            Fieldsblobs newRecord = new Fieldsblobs();
            newRecord.setFirstname("Scott");
            newRecord.setLastname("Doe");
            FieldsblobsCriteria example = new FieldsblobsCriteria();
            example.createCriteria().andFirstnameLike("S%");
            int rows = dao.updateByCriteriaWithBLOBs(newRecord, example);
            assertEquals(1, rows);
            
            List answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
            
            Fieldsblobs returnedRecord = (Fieldsblobs) answer.get(0);
            
            assertEquals(newRecord.getFirstname(), returnedRecord.getFirstname());
            assertEquals(newRecord.getLastname(), returnedRecord.getLastname());
            assertNull(returnedRecord.getBlob1());
            assertNull(returnedRecord.getBlob2());
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public void testAwfulTableUpdateByCriteriaSelective() {
        AwfulTableDAO dao = getAwfulTableDAO();
    
        try {
            AwfulTable record = new AwfulTable();
            record.seteMail("fred@fred.com");
            record.setEmailaddress("alsofred@fred.com");
            record.setFirstFirstName("fred1");
            record.setFrom("from field");
            record.setId1(new Integer(1));
            record.setId2(new Integer(2));
            record.setId5(new Integer(5));
            record.setId6(new Integer(6));
            record.setId7(new Integer(7));
            record.setSecondFirstName("fred2");
            record.setThirdFirstName("fred3");
    
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("fred2@fred.com");
            record.setEmailaddress("alsofred2@fred.com");
            record.setFirstFirstName("fred11");
            record.setFrom("from from field");
            record.setId1(new Integer(11));
            record.setId2(new Integer(22));
            record.setId5(new Integer(55));
            record.setId6(new Integer(66));
            record.setId7(new Integer(77));
            record.setSecondFirstName("fred22");
            record.setThirdFirstName("fred33");
    
            dao.insert(record);
    
            AwfulTable newRecord = new AwfulTable();
            newRecord.setFirstFirstName("Alonzo");
            AwfulTableCriteria example = new AwfulTableCriteria();
            example.createCriteria().andEMailLike("fred2@%");
            int rows = dao.updateByCriteriaSelective(newRecord, example);
            assertEquals(1, rows);
    
            List answer = dao.selectByCriteria(example);
            assertEquals(1, answer.size());

            AwfulTable returnedRecord = (AwfulTable) answer.get(0);
            
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
            
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    public void testAwfulTableUpdateByCriteria() {
        AwfulTableDAO dao = getAwfulTableDAO();
    
        try {
            AwfulTable record = new AwfulTable();
            record.seteMail("fred@fred.com");
            record.setEmailaddress("alsofred@fred.com");
            record.setFirstFirstName("fred1");
            record.setFrom("from field");
            record.setId1(new Integer(1));
            record.setId2(new Integer(2));
            record.setId5(new Integer(5));
            record.setId6(new Integer(6));
            record.setId7(new Integer(7));
            record.setSecondFirstName("fred2");
            record.setThirdFirstName("fred3");
    
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("fred2@fred.com");
            record.setEmailaddress("alsofred2@fred.com");
            record.setFirstFirstName("fred11");
            record.setFrom("from from field");
            record.setId1(new Integer(11));
            record.setId2(new Integer(22));
            record.setId5(new Integer(55));
            record.setId6(new Integer(66));
            record.setId7(new Integer(77));
            record.setSecondFirstName("fred22");
            record.setThirdFirstName("fred33");
    
            dao.insert(record);
    
            AwfulTable newRecord = new AwfulTable();
            newRecord.setFirstFirstName("Alonzo");
            newRecord.setCustomerId(new Integer(58));
            newRecord.setId1(new Integer(111));
            newRecord.setId2(new Integer(222));
            newRecord.setId5(new Integer(555));
            newRecord.setId6(new Integer(666));
            newRecord.setId7(new Integer(777));
            AwfulTableCriteria example = new AwfulTableCriteria();
            example.createCriteria().andEMailLike("fred2@%");
            int rows = dao.updateByCriteria(newRecord, example);
            assertEquals(1, rows);

            example.clear();
            example.createCriteria().andCustomerIdEqualTo(new Integer(58));
            List answer = dao.selectByCriteria(example);
            assertEquals(1, answer.size());

            AwfulTable returnedRecord = (AwfulTable) answer.get(0);
            
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
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }
}
