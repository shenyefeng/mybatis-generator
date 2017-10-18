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
package mbg.test.ib2j2.conditional;

import java.util.ArrayList;
import java.util.List;

import mbg.test.common.util.TestUtilities;
import mbg.test.ib2j2.generated.conditional.dao.AwfulTableDAO;
import mbg.test.ib2j2.generated.conditional.dao.FieldsblobsDAO;
import mbg.test.ib2j2.generated.conditional.dao.FieldsonlyDAO;
import mbg.test.ib2j2.generated.conditional.dao.PkblobsDAO;
import mbg.test.ib2j2.generated.conditional.dao.PkfieldsDAO;
import mbg.test.ib2j2.generated.conditional.dao.PkfieldsblobsDAO;
import mbg.test.ib2j2.generated.conditional.dao.PkonlyDAO;
import mbg.test.ib2j2.generated.conditional.model.AwfulTable;
import mbg.test.ib2j2.generated.conditional.model.AwfulTableCriteria;
import mbg.test.ib2j2.generated.conditional.model.Fieldsblobs;
import mbg.test.ib2j2.generated.conditional.model.FieldsblobsCriteria;
import mbg.test.ib2j2.generated.conditional.model.FieldsblobsWithBLOBs;
import mbg.test.ib2j2.generated.conditional.model.Fieldsonly;
import mbg.test.ib2j2.generated.conditional.model.FieldsonlyCriteria;
import mbg.test.ib2j2.generated.conditional.model.Pkblobs;
import mbg.test.ib2j2.generated.conditional.model.PkblobsCriteria;
import mbg.test.ib2j2.generated.conditional.model.Pkfields;
import mbg.test.ib2j2.generated.conditional.model.PkfieldsCriteria;
import mbg.test.ib2j2.generated.conditional.model.Pkfieldsblobs;
import mbg.test.ib2j2.generated.conditional.model.PkfieldsblobsCriteria;
import mbg.test.ib2j2.generated.conditional.model.PkonlyCriteria;
import mbg.test.ib2j2.generated.conditional.model.PkonlyKey;

public class SelectByCriteriaTest extends AbstractConditionalJava2Test {

    public void testFieldsOnlySelectByCriteria() {
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
    
            FieldsonlyCriteria example = new FieldsonlyCriteria();
            example.createCriteria().andIntegerfieldGreaterThan(new Integer(5));
    
            List answer = dao.selectByCriteria(example);
            assertEquals(2, answer.size());
    
            example = new FieldsonlyCriteria();
            answer = dao.selectByCriteria(example);
            assertEquals(3, answer.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testFieldsOnlySelectByCriteriaNoCriteria() {
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
    
            FieldsonlyCriteria example = new FieldsonlyCriteria();
            example.createCriteria();
    
            List answer = dao.selectByCriteria(example);
            assertEquals(3, answer.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testPKOnlySelectByCriteria() {
        PkonlyDAO dao = getPkonlyDAO();
    
        try {
            PkonlyKey key = new PkonlyKey();
            key.setId(new Integer(1));
            key.setSeqNum(new Integer(3));
            dao.insert(key);
    
            key = new PkonlyKey();
            key.setId(new Integer(5));
            key.setSeqNum(new Integer(6));
            dao.insert(key);
    
            key = new PkonlyKey();
            key.setId(new Integer(7));
            key.setSeqNum(new Integer(8));
            dao.insert(key);
    
            PkonlyCriteria example = new PkonlyCriteria();
            example.createCriteria().andIdGreaterThan(new Integer(4));
            List answer = dao.selectByCriteria(example);
            assertEquals(2, answer.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testPKOnlySelectByCriteriaNoCriteria() {
        PkonlyDAO dao = getPkonlyDAO();
    
        try {
            PkonlyKey key = new PkonlyKey();
            key.setId(new Integer(1));
            key.setSeqNum(new Integer(3));
            dao.insert(key);
    
            key = new PkonlyKey();
            key.setId(new Integer(5));
            key.setSeqNum(new Integer(6));
            dao.insert(key);
    
            key = new PkonlyKey();
            key.setId(new Integer(7));
            key.setSeqNum(new Integer(8));
            dao.insert(key);
    
            PkonlyCriteria example = new PkonlyCriteria();
            example.createCriteria();
            List answer = dao.selectByCriteria(example);
            assertEquals(3, answer.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testPKFieldsSelectByCriteriaLike() {
        PkfieldsDAO dao = getPkfieldsDAO();
    
        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Fred");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(1));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Wilma");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(2));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Pebbles");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(3));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Barney");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(1));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Betty");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(2));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Bamm Bamm");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(3));
            dao.insert(record);
    
            PkfieldsCriteria example = new PkfieldsCriteria();
            example.createCriteria().andFirstnameLike("B%");
            example.setOrderByClause("ID1, ID2");
            List answer = dao.selectByCriteria(example);
            assertEquals(3, answer.size());
            Pkfields returnedRecord = (Pkfields) answer.get(0);
            assertEquals(2, returnedRecord.getId1().intValue());
            assertEquals(1, returnedRecord.getId2().intValue());
            returnedRecord = (Pkfields) answer.get(1);
            assertEquals(2, returnedRecord.getId1().intValue());
            assertEquals(2, returnedRecord.getId2().intValue());
            returnedRecord = (Pkfields) answer.get(2);
            assertEquals(2, returnedRecord.getId1().intValue());
            assertEquals(3, returnedRecord.getId2().intValue());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testPKFieldsSelectByCriteriaNotLike() {
        PkfieldsDAO dao = getPkfieldsDAO();
    
        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Fred");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(1));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Wilma");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(2));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Pebbles");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(3));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Barney");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(1));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Betty");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(2));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Bamm Bamm");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(3));
            dao.insert(record);
    
            PkfieldsCriteria example = new PkfieldsCriteria();
            example.createCriteria().andFirstnameNotLike("B%");
            example.setOrderByClause("ID1, ID2");
            List answer = dao.selectByCriteria(example);
            assertEquals(3, answer.size());
            Pkfields returnedRecord = (Pkfields) answer.get(0);
            assertEquals(1, returnedRecord.getId1().intValue());
            assertEquals(1, returnedRecord.getId2().intValue());
            returnedRecord = (Pkfields) answer.get(1);
            assertEquals(1, returnedRecord.getId1().intValue());
            assertEquals(2, returnedRecord.getId2().intValue());
            returnedRecord = (Pkfields) answer.get(2);
            assertEquals(1, returnedRecord.getId1().intValue());
            assertEquals(3, returnedRecord.getId2().intValue());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testPKFieldsSelectByCriteriaComplexLike() {
        PkfieldsDAO dao = getPkfieldsDAO();
    
        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Fred");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(1));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Wilma");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(2));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Pebbles");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(3));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Barney");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(1));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Betty");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(2));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Bamm Bamm");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(3));
            dao.insert(record);
    
            PkfieldsCriteria example = new PkfieldsCriteria();
            example.createCriteria().andFirstnameLike("B%").andId2EqualTo(
                    new Integer(3));
            example.or(example.createCriteria().andFirstnameLike("Wi%"));
    
            example.setOrderByClause("ID1, ID2");
            List answer = dao.selectByCriteria(example);
            assertEquals(2, answer.size());
            Pkfields returnedRecord = (Pkfields) answer.get(0);
            assertEquals(1, returnedRecord.getId1().intValue());
            assertEquals(2, returnedRecord.getId2().intValue());
            returnedRecord = (Pkfields) answer.get(1);
            assertEquals(2, returnedRecord.getId1().intValue());
            assertEquals(3, returnedRecord.getId2().intValue());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testPKFieldsSelectByCriteriaIn() {
        PkfieldsDAO dao = getPkfieldsDAO();
    
        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Fred");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(1));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Wilma");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(2));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Pebbles");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(3));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Barney");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(1));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Betty");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(2));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Bamm Bamm");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(3));
            dao.insert(record);
    
            List ids = new ArrayList();
            ids.add(new Integer(1));
            ids.add(new Integer(3));
    
            PkfieldsCriteria example = new PkfieldsCriteria();
            example.createCriteria().andId2In(ids);
    
            example.setOrderByClause("ID1, ID2");
            List answer = dao.selectByCriteria(example);
            assertEquals(4, answer.size());
            Pkfields returnedRecord = (Pkfields) answer.get(0);
            assertEquals(1, returnedRecord.getId1().intValue());
            assertEquals(1, returnedRecord.getId2().intValue());
            returnedRecord = (Pkfields) answer.get(1);
            assertEquals(1, returnedRecord.getId1().intValue());
            assertEquals(3, returnedRecord.getId2().intValue());
            returnedRecord = (Pkfields) answer.get(2);
            assertEquals(2, returnedRecord.getId1().intValue());
            assertEquals(1, returnedRecord.getId2().intValue());
            returnedRecord = (Pkfields) answer.get(3);
            assertEquals(2, returnedRecord.getId1().intValue());
            assertEquals(3, returnedRecord.getId2().intValue());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testPKFieldsSelectByCriteriaBetween() {
        PkfieldsDAO dao = getPkfieldsDAO();
    
        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Fred");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(1));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Wilma");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(2));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Pebbles");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(3));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Barney");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(1));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Betty");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(2));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Bamm Bamm");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(3));
            dao.insert(record);
    
            PkfieldsCriteria example = new PkfieldsCriteria();
            example.createCriteria().andId2Between(new Integer(1),
                    new Integer(3));
    
            example.setOrderByClause("ID1, ID2");
            List answer = dao.selectByCriteria(example);
            assertEquals(6, answer.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testPKFieldsSelectByCriteriaNoCriteria() {
        PkfieldsDAO dao = getPkfieldsDAO();
    
        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Fred");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(1));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Wilma");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(2));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Pebbles");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(3));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Barney");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(1));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Betty");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(2));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Bamm Bamm");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(3));
            dao.insert(record);
    
            PkfieldsCriteria example = new PkfieldsCriteria();
            example.createCriteria();
    
            example.setOrderByClause("ID1, ID2");
            List answer = dao.selectByCriteria(example);
            assertEquals(6, answer.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testPKFieldsSelectByCriteriaEscapedFields() {
        PkfieldsDAO dao = getPkfieldsDAO();
    
        try {
            Pkfields record = new Pkfields();
            record.setFirstname("Fred");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(1));
            record.setWierdField(new Integer(11));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Wilma");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(2));
            record.setWierdField(new Integer(22));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Pebbles");
            record.setLastname("Flintstone");
            record.setId1(new Integer(1));
            record.setId2(new Integer(3));
            record.setWierdField(new Integer(33));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Barney");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(1));
            record.setWierdField(new Integer(44));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Betty");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(2));
            record.setWierdField(new Integer(55));
            dao.insert(record);
    
            record = new Pkfields();
            record.setFirstname("Bamm Bamm");
            record.setLastname("Rubble");
            record.setId1(new Integer(2));
            record.setId2(new Integer(3));
            record.setWierdField(new Integer(66));
            dao.insert(record);
    
            List values = new ArrayList();
            values.add(new Integer(11));
            values.add(new Integer(22));
    
            PkfieldsCriteria example = new PkfieldsCriteria();
            example.createCriteria().andWierdFieldLessThan(new Integer(40))
                    .andWierdFieldIn(values);
    
            example.setOrderByClause("ID1, ID2");
            List answer = dao.selectByCriteria(example);
            assertEquals(2, answer.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testPKBlobsSelectByCriteriaWithoutBlobs() {
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
    
            PkblobsCriteria example = new PkblobsCriteria();
            example.createCriteria().andIdGreaterThan(new Integer(4));
            List answer = dao.selectByCriteriaWithoutBLOBs(example);
    
            assertEquals(1, answer.size());
    
            Pkblobs key = (Pkblobs) answer.get(0);
            assertEquals(6, key.getId().intValue());
            assertNull(key.getBlob1());
            assertNull(key.getBlob2());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testPKBlobsSelectByCriteriaWithoutBlobsNoCriteria() {
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
    
            PkblobsCriteria example = new PkblobsCriteria();
            example.createCriteria();
            List answer = dao.selectByCriteriaWithoutBLOBs(example);
    
            assertEquals(2, answer.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testPKBlobsSelectByCriteriaWithBlobs() {
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
    
            PkblobsCriteria example = new PkblobsCriteria();
            example.createCriteria().andIdGreaterThan(new Integer(4));
            List answer = dao.selectByCriteriaWithBLOBs(example);
    
            assertEquals(1, answer.size());
    
            Pkblobs newRecord = (Pkblobs) answer.get(0);
            assertEquals(record.getId(), newRecord.getId());
            assertTrue(TestUtilities.blobsAreEqual(record.getBlob1(), newRecord.getBlob1()));
            assertTrue(TestUtilities.blobsAreEqual(record.getBlob2(), newRecord.getBlob2()));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testPKFieldsBlobsSelectByCriteriaWithoutBlobs() {
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
    
            PkfieldsblobsCriteria example = new PkfieldsblobsCriteria();
            example.createCriteria().andId2EqualTo(new Integer(6));
            List answer = dao.selectByCriteriaWithoutBLOBs(example);
            assertEquals(1, answer.size());
    
            Pkfieldsblobs newRecord = (Pkfieldsblobs) answer.get(0);
            assertEquals(record.getId1(), newRecord.getId1());
            assertEquals(record.getId2(), newRecord.getId2());
            assertEquals(record.getFirstname(), newRecord.getFirstname());
            assertEquals(record.getLastname(), newRecord.getLastname());
            assertNull(newRecord.getBlob1());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testPKFieldsBlobsSelectByCriteriaWithBlobs() {
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
    
            PkfieldsblobsCriteria example = new PkfieldsblobsCriteria();
            example.createCriteria().andId2EqualTo(new Integer(6));
            List answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
    
            Pkfieldsblobs newRecord = (Pkfieldsblobs) answer.get(0);
            assertEquals(record.getId1(), newRecord.getId1());
            assertEquals(record.getId2(), newRecord.getId2());
            assertEquals(record.getFirstname(), newRecord.getFirstname());
            assertEquals(record.getLastname(), newRecord.getLastname());
            assertTrue(TestUtilities.blobsAreEqual(record.getBlob1(), newRecord.getBlob1()));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testPKFieldsBlobsSelectByCriteriaWithBlobsNoCriteria() {
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
    
            PkfieldsblobsCriteria example = new PkfieldsblobsCriteria();
            example.createCriteria();
            List answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(2, answer.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testFieldsBlobsSelectByCriteriaWithoutBlobs() {
        FieldsblobsDAO dao = getFieldsblobsDAO();
    
        try {
            FieldsblobsWithBLOBs record = new FieldsblobsWithBLOBs();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(TestUtilities.generateRandomBlob());
            record.setBlob2(TestUtilities.generateRandomBlob());
            dao.insert(record);
    
            record = new FieldsblobsWithBLOBs();
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(TestUtilities.generateRandomBlob());
            record.setBlob2(TestUtilities.generateRandomBlob());
            dao.insert(record);
    
            FieldsblobsCriteria example = new FieldsblobsCriteria();
            example.createCriteria().andFirstnameLike("S%");
            List answer = dao.selectByCriteriaWithoutBLOBs(example);
            assertEquals(1, answer.size());
    
            Fieldsblobs newRecord = (Fieldsblobs) answer.get(0);
            assertFalse(newRecord instanceof FieldsblobsWithBLOBs);
            assertEquals(record.getFirstname(), newRecord.getFirstname());
            assertEquals(record.getLastname(), newRecord.getLastname());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testFieldsBlobsSelectByCriteriaWithBlobs() {
        FieldsblobsDAO dao = getFieldsblobsDAO();
    
        try {
            FieldsblobsWithBLOBs record = new FieldsblobsWithBLOBs();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(TestUtilities.generateRandomBlob());
            record.setBlob2(TestUtilities.generateRandomBlob());
            dao.insert(record);
    
            record = new FieldsblobsWithBLOBs();
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(TestUtilities.generateRandomBlob());
            record.setBlob2(TestUtilities.generateRandomBlob());
            dao.insert(record);
    
            FieldsblobsCriteria example = new FieldsblobsCriteria();
            example.createCriteria().andFirstnameLike("S%");
            List answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(1, answer.size());
    
            FieldsblobsWithBLOBs newRecord = (FieldsblobsWithBLOBs) answer
                    .get(0);
            assertEquals(record.getFirstname(), newRecord.getFirstname());
            assertEquals(record.getLastname(), newRecord.getLastname());
            assertTrue(TestUtilities.blobsAreEqual(record.getBlob1(), newRecord.getBlob1()));
            assertTrue(TestUtilities.blobsAreEqual(record.getBlob2(), newRecord.getBlob2()));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testFieldsBlobsSelectByCriteriaWithBlobsNoCriteria() {
        FieldsblobsDAO dao = getFieldsblobsDAO();
    
        try {
            FieldsblobsWithBLOBs record = new FieldsblobsWithBLOBs();
            record.setFirstname("Jeff");
            record.setLastname("Smith");
            record.setBlob1(TestUtilities.generateRandomBlob());
            record.setBlob2(TestUtilities.generateRandomBlob());
            dao.insert(record);
    
            record = new FieldsblobsWithBLOBs();
            record.setFirstname("Scott");
            record.setLastname("Jones");
            record.setBlob1(TestUtilities.generateRandomBlob());
            record.setBlob2(TestUtilities.generateRandomBlob());
            dao.insert(record);
    
            FieldsblobsCriteria example = new FieldsblobsCriteria();
            example.createCriteria();
            List answer = dao.selectByCriteriaWithBLOBs(example);
            assertEquals(2, answer.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testAwfulTableSelectByCriteriaLike() {
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
            record.seteMail("wilma@wilma.com");
            record.setEmailaddress("alsoWilma@wilma.com");
            record.setFirstFirstName("wilma1");
            record.setFrom("from field");
            record.setId1(new Integer(11));
            record.setId2(new Integer(22));
            record.setId5(new Integer(55));
            record.setId6(new Integer(66));
            record.setId7(new Integer(77));
            record.setSecondFirstName("wilma2");
            record.setThirdFirstName("wilma3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("pebbles@pebbles.com");
            record.setEmailaddress("alsoPebbles@pebbles.com");
            record.setFirstFirstName("pebbles1");
            record.setFrom("from field");
            record.setId1(new Integer(111));
            record.setId2(new Integer(222));
            record.setId5(new Integer(555));
            record.setId6(new Integer(666));
            record.setId7(new Integer(777));
            record.setSecondFirstName("pebbles2");
            record.setThirdFirstName("pebbles3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("barney@barney.com");
            record.setEmailaddress("alsoBarney@barney.com");
            record.setFirstFirstName("barney1");
            record.setFrom("from field");
            record.setId1(new Integer(1111));
            record.setId2(new Integer(2222));
            record.setId5(new Integer(5555));
            record.setId6(new Integer(6666));
            record.setId7(new Integer(7777));
            record.setSecondFirstName("barney2");
            record.setThirdFirstName("barney3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("betty@betty.com");
            record.setEmailaddress("alsoBetty@betty.com");
            record.setFirstFirstName("betty1");
            record.setFrom("from field");
            record.setId1(new Integer(11111));
            record.setId2(new Integer(22222));
            record.setId5(new Integer(55555));
            record.setId6(new Integer(66666));
            record.setId7(new Integer(77777));
            record.setSecondFirstName("betty2");
            record.setThirdFirstName("betty3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("bammbamm@bammbamm.com");
            record.setEmailaddress("alsoBammbamm@bammbamm.com");
            record.setFirstFirstName("bammbamm1");
            record.setFrom("from field");
            record.setId1(new Integer(111111));
            record.setId2(new Integer(222222));
            record.setId5(new Integer(555555));
            record.setId6(new Integer(666666));
            record.setId7(new Integer(777777));
            record.setSecondFirstName("bammbamm2");
            record.setThirdFirstName("bammbamm3");
            dao.insert(record);
    
            AwfulTableCriteria example = new AwfulTableCriteria();
            example.createCriteria().andFirstFirstNameLike("b%");
            example.setOrderByClause("\"A_CuStOmEr iD\"");
            List answer = dao.selectByCriteria(example);
            assertEquals(3, answer.size());
            AwfulTable returnedRecord = (AwfulTable) answer.get(0);
            assertEquals(1111, returnedRecord.getId1().intValue());
            assertEquals(2222, returnedRecord.getId2().intValue());
            returnedRecord = (AwfulTable) answer.get(1);
            assertEquals(11111, returnedRecord.getId1().intValue());
            assertEquals(22222, returnedRecord.getId2().intValue());
            returnedRecord = (AwfulTable) answer.get(2);
            assertEquals(111111, returnedRecord.getId1().intValue());
            assertEquals(222222, returnedRecord.getId2().intValue());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testAwfulTableSelectByCriteriaNotLike() {
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
            record.seteMail("wilma@wilma.com");
            record.setEmailaddress("alsoWilma@wilma.com");
            record.setFirstFirstName("wilma1");
            record.setFrom("from field");
            record.setId1(new Integer(11));
            record.setId2(new Integer(22));
            record.setId5(new Integer(55));
            record.setId6(new Integer(66));
            record.setId7(new Integer(77));
            record.setSecondFirstName("wilma2");
            record.setThirdFirstName("wilma3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("pebbles@pebbles.com");
            record.setEmailaddress("alsoPebbles@pebbles.com");
            record.setFirstFirstName("pebbles1");
            record.setFrom("from field");
            record.setId1(new Integer(111));
            record.setId2(new Integer(222));
            record.setId5(new Integer(555));
            record.setId6(new Integer(666));
            record.setId7(new Integer(777));
            record.setSecondFirstName("pebbles2");
            record.setThirdFirstName("pebbles3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("barney@barney.com");
            record.setEmailaddress("alsoBarney@barney.com");
            record.setFirstFirstName("barney1");
            record.setFrom("from field");
            record.setId1(new Integer(1111));
            record.setId2(new Integer(2222));
            record.setId5(new Integer(5555));
            record.setId6(new Integer(6666));
            record.setId7(new Integer(7777));
            record.setSecondFirstName("barney2");
            record.setThirdFirstName("barney3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("betty@betty.com");
            record.setEmailaddress("alsoBetty@betty.com");
            record.setFirstFirstName("betty1");
            record.setFrom("from field");
            record.setId1(new Integer(11111));
            record.setId2(new Integer(22222));
            record.setId5(new Integer(55555));
            record.setId6(new Integer(66666));
            record.setId7(new Integer(77777));
            record.setSecondFirstName("betty2");
            record.setThirdFirstName("betty3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("bammbamm@bammbamm.com");
            record.setEmailaddress("alsoBammbamm@bammbamm.com");
            record.setFirstFirstName("bammbamm1");
            record.setFrom("from field");
            record.setId1(new Integer(111111));
            record.setId2(new Integer(222222));
            record.setId5(new Integer(555555));
            record.setId6(new Integer(666666));
            record.setId7(new Integer(777777));
            record.setSecondFirstName("bammbamm2");
            record.setThirdFirstName("bammbamm3");
            dao.insert(record);
    
            AwfulTableCriteria example = new AwfulTableCriteria();
            example.createCriteria().andFirstFirstNameNotLike("b%");
            example.setOrderByClause("\"A_CuStOmEr iD\"");
            List answer = dao.selectByCriteria(example);
            assertEquals(3, answer.size());
            AwfulTable returnedRecord = (AwfulTable) answer.get(0);
            assertEquals(1, returnedRecord.getId1().intValue());
            assertEquals(2, returnedRecord.getId2().intValue());
            returnedRecord = (AwfulTable) answer.get(1);
            assertEquals(11, returnedRecord.getId1().intValue());
            assertEquals(22, returnedRecord.getId2().intValue());
            returnedRecord = (AwfulTable) answer.get(2);
            assertEquals(111, returnedRecord.getId1().intValue());
            assertEquals(222, returnedRecord.getId2().intValue());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testAwfulTableSelectByCriteriaComplexLike() {
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
            record.seteMail("wilma@wilma.com");
            record.setEmailaddress("alsoWilma@wilma.com");
            record.setFirstFirstName("wilma1");
            record.setFrom("from field");
            record.setId1(new Integer(11));
            record.setId2(new Integer(22));
            record.setId5(new Integer(55));
            record.setId6(new Integer(66));
            record.setId7(new Integer(77));
            record.setSecondFirstName("wilma2");
            record.setThirdFirstName("wilma3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("pebbles@pebbles.com");
            record.setEmailaddress("alsoPebbles@pebbles.com");
            record.setFirstFirstName("pebbles1");
            record.setFrom("from field");
            record.setId1(new Integer(111));
            record.setId2(new Integer(222));
            record.setId5(new Integer(555));
            record.setId6(new Integer(666));
            record.setId7(new Integer(777));
            record.setSecondFirstName("pebbles2");
            record.setThirdFirstName("pebbles3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("barney@barney.com");
            record.setEmailaddress("alsoBarney@barney.com");
            record.setFirstFirstName("barney1");
            record.setFrom("from field");
            record.setId1(new Integer(1111));
            record.setId2(new Integer(2222));
            record.setId5(new Integer(5555));
            record.setId6(new Integer(6666));
            record.setId7(new Integer(7777));
            record.setSecondFirstName("barney2");
            record.setThirdFirstName("barney3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("betty@betty.com");
            record.setEmailaddress("alsoBetty@betty.com");
            record.setFirstFirstName("betty1");
            record.setFrom("from field");
            record.setId1(new Integer(11111));
            record.setId2(new Integer(22222));
            record.setId5(new Integer(55555));
            record.setId6(new Integer(66666));
            record.setId7(new Integer(77777));
            record.setSecondFirstName("betty2");
            record.setThirdFirstName("betty3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("bammbamm@bammbamm.com");
            record.setEmailaddress("alsoBammbamm@bammbamm.com");
            record.setFirstFirstName("bammbamm1");
            record.setFrom("from field");
            record.setId1(new Integer(111111));
            record.setId2(new Integer(222222));
            record.setId5(new Integer(555555));
            record.setId6(new Integer(666666));
            record.setId7(new Integer(777777));
            record.setSecondFirstName("bammbamm2");
            record.setThirdFirstName("bammbamm3");
            dao.insert(record);
    
            AwfulTableCriteria example = new AwfulTableCriteria();
            example.or().andFirstFirstNameLike("b%").andId2EqualTo(new Integer(222222));
            example.or().andFirstFirstNameLike("wi%");
            example.setOrderByClause("\"A_CuStOmEr iD\"");
            List answer = dao.selectByCriteria(example);
            assertEquals(2, answer.size());
            AwfulTable returnedRecord = (AwfulTable) answer.get(0);
            assertEquals(11, returnedRecord.getId1().intValue());
            assertEquals(22, returnedRecord.getId2().intValue());
            returnedRecord = (AwfulTable) answer.get(1);
            assertEquals(111111, returnedRecord.getId1().intValue());
            assertEquals(222222, returnedRecord.getId2().intValue());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testAwfulTableSelectByCriteriaIn() {
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
            record.seteMail("wilma@wilma.com");
            record.setEmailaddress("alsoWilma@wilma.com");
            record.setFirstFirstName("wilma1");
            record.setFrom("from field");
            record.setId1(new Integer(11));
            record.setId2(new Integer(22));
            record.setId5(new Integer(55));
            record.setId6(new Integer(66));
            record.setId7(new Integer(77));
            record.setSecondFirstName("wilma2");
            record.setThirdFirstName("wilma3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("pebbles@pebbles.com");
            record.setEmailaddress("alsoPebbles@pebbles.com");
            record.setFirstFirstName("pebbles1");
            record.setFrom("from field");
            record.setId1(new Integer(111));
            record.setId2(new Integer(222));
            record.setId5(new Integer(555));
            record.setId6(new Integer(666));
            record.setId7(new Integer(777));
            record.setSecondFirstName("pebbles2");
            record.setThirdFirstName("pebbles3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("barney@barney.com");
            record.setEmailaddress("alsoBarney@barney.com");
            record.setFirstFirstName("barney1");
            record.setFrom("from field");
            record.setId1(new Integer(1111));
            record.setId2(new Integer(2222));
            record.setId5(new Integer(5555));
            record.setId6(new Integer(6666));
            record.setId7(new Integer(7777));
            record.setSecondFirstName("barney2");
            record.setThirdFirstName("barney3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("betty@betty.com");
            record.setEmailaddress("alsoBetty@betty.com");
            record.setFirstFirstName("betty1");
            record.setFrom("from field");
            record.setId1(new Integer(11111));
            record.setId2(new Integer(22222));
            record.setId5(new Integer(55555));
            record.setId6(new Integer(66666));
            record.setId7(new Integer(77777));
            record.setSecondFirstName("betty2");
            record.setThirdFirstName("betty3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("bammbamm@bammbamm.com");
            record.setEmailaddress("alsoBammbamm@bammbamm.com");
            record.setFirstFirstName("bammbamm1");
            record.setFrom("from field");
            record.setId1(new Integer(111111));
            record.setId2(new Integer(222222));
            record.setId5(new Integer(555555));
            record.setId6(new Integer(666666));
            record.setId7(new Integer(777777));
            record.setSecondFirstName("bammbamm2");
            record.setThirdFirstName("bammbamm3");
            dao.insert(record);
    
            List ids = new ArrayList();
            ids.add(new Integer(1));
            ids.add(new Integer(11));
    
            AwfulTableCriteria example = new AwfulTableCriteria();
            example.createCriteria().andId1In(ids);
            example.setOrderByClause("\"A_CuStOmEr iD\"");
            List answer = dao.selectByCriteria(example);
            
            assertEquals(2, answer.size());
            AwfulTable returnedRecord = (AwfulTable) answer.get(0);
            assertEquals(1, returnedRecord.getId1().intValue());
            assertEquals(2, returnedRecord.getId2().intValue());
            returnedRecord = (AwfulTable) answer.get(1);
            assertEquals(11, returnedRecord.getId1().intValue());
            assertEquals(22, returnedRecord.getId2().intValue());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testAwfulTableSelectByCriteriaBetween() {
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
            record.seteMail("wilma@wilma.com");
            record.setEmailaddress("alsoWilma@wilma.com");
            record.setFirstFirstName("wilma1");
            record.setFrom("from field");
            record.setId1(new Integer(11));
            record.setId2(new Integer(22));
            record.setId5(new Integer(55));
            record.setId6(new Integer(66));
            record.setId7(new Integer(77));
            record.setSecondFirstName("wilma2");
            record.setThirdFirstName("wilma3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("pebbles@pebbles.com");
            record.setEmailaddress("alsoPebbles@pebbles.com");
            record.setFirstFirstName("pebbles1");
            record.setFrom("from field");
            record.setId1(new Integer(111));
            record.setId2(new Integer(222));
            record.setId5(new Integer(555));
            record.setId6(new Integer(666));
            record.setId7(new Integer(777));
            record.setSecondFirstName("pebbles2");
            record.setThirdFirstName("pebbles3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("barney@barney.com");
            record.setEmailaddress("alsoBarney@barney.com");
            record.setFirstFirstName("barney1");
            record.setFrom("from field");
            record.setId1(new Integer(1111));
            record.setId2(new Integer(2222));
            record.setId5(new Integer(5555));
            record.setId6(new Integer(6666));
            record.setId7(new Integer(7777));
            record.setSecondFirstName("barney2");
            record.setThirdFirstName("barney3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("betty@betty.com");
            record.setEmailaddress("alsoBetty@betty.com");
            record.setFirstFirstName("betty1");
            record.setFrom("from field");
            record.setId1(new Integer(11111));
            record.setId2(new Integer(22222));
            record.setId5(new Integer(55555));
            record.setId6(new Integer(66666));
            record.setId7(new Integer(77777));
            record.setSecondFirstName("betty2");
            record.setThirdFirstName("betty3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("bammbamm@bammbamm.com");
            record.setEmailaddress("alsoBammbamm@bammbamm.com");
            record.setFirstFirstName("bammbamm1");
            record.setFrom("from field");
            record.setId1(new Integer(111111));
            record.setId2(new Integer(222222));
            record.setId5(new Integer(555555));
            record.setId6(new Integer(666666));
            record.setId7(new Integer(777777));
            record.setSecondFirstName("bammbamm2");
            record.setThirdFirstName("bammbamm3");
            dao.insert(record);
    
            AwfulTableCriteria example = new AwfulTableCriteria();
            example.createCriteria().andId1Between(new Integer(1), new Integer(1000));
            List answer = dao.selectByCriteria(example);
            assertEquals(3, answer.size());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testAwfulTableSelectByCriteriaNoCriteria() {
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
            record.seteMail("wilma@wilma.com");
            record.setEmailaddress("alsoWilma@wilma.com");
            record.setFirstFirstName("wilma1");
            record.setFrom("from field");
            record.setId1(new Integer(11));
            record.setId2(new Integer(22));
            record.setId5(new Integer(55));
            record.setId6(new Integer(66));
            record.setId7(new Integer(77));
            record.setSecondFirstName("wilma2");
            record.setThirdFirstName("wilma3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("pebbles@pebbles.com");
            record.setEmailaddress("alsoPebbles@pebbles.com");
            record.setFirstFirstName("pebbles1");
            record.setFrom("from field");
            record.setId1(new Integer(111));
            record.setId2(new Integer(222));
            record.setId5(new Integer(555));
            record.setId6(new Integer(666));
            record.setId7(new Integer(777));
            record.setSecondFirstName("pebbles2");
            record.setThirdFirstName("pebbles3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("barney@barney.com");
            record.setEmailaddress("alsoBarney@barney.com");
            record.setFirstFirstName("barney1");
            record.setFrom("from field");
            record.setId1(new Integer(1111));
            record.setId2(new Integer(2222));
            record.setId5(new Integer(5555));
            record.setId6(new Integer(6666));
            record.setId7(new Integer(7777));
            record.setSecondFirstName("barney2");
            record.setThirdFirstName("barney3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("betty@betty.com");
            record.setEmailaddress("alsoBetty@betty.com");
            record.setFirstFirstName("betty1");
            record.setFrom("from field");
            record.setId1(new Integer(11111));
            record.setId2(new Integer(22222));
            record.setId5(new Integer(55555));
            record.setId6(new Integer(66666));
            record.setId7(new Integer(77777));
            record.setSecondFirstName("betty2");
            record.setThirdFirstName("betty3");
            dao.insert(record);
    
            record = new AwfulTable();
            record.seteMail("bammbamm@bammbamm.com");
            record.setEmailaddress("alsoBammbamm@bammbamm.com");
            record.setFirstFirstName("bammbamm1");
            record.setFrom("from field");
            record.setId1(new Integer(111111));
            record.setId2(new Integer(222222));
            record.setId5(new Integer(555555));
            record.setId6(new Integer(666666));
            record.setId7(new Integer(777777));
            record.setSecondFirstName("bammbamm2");
            record.setThirdFirstName("bammbamm3");
            dao.insert(record);
    
            AwfulTableCriteria example = new AwfulTableCriteria();
            example.createCriteria();
            example.setOrderByClause("\"A_CuStOmEr iD\" desc");
            
            List answer = dao.selectByCriteria(example);
            assertEquals(6, answer.size());
            AwfulTable returnedRecord = (AwfulTable) answer.get(0);
            assertEquals(111111, returnedRecord.getId1().intValue());
            returnedRecord = (AwfulTable) answer.get(1);
            assertEquals(11111, returnedRecord.getId1().intValue());
            returnedRecord = (AwfulTable) answer.get(2);
            assertEquals(1111, returnedRecord.getId1().intValue());
            returnedRecord = (AwfulTable) answer.get(3);
            assertEquals(111, returnedRecord.getId1().intValue());
            returnedRecord = (AwfulTable) answer.get(4);
            assertEquals(11, returnedRecord.getId1().intValue());
            returnedRecord = (AwfulTable) answer.get(5);
            assertEquals(1, returnedRecord.getId1().intValue());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
