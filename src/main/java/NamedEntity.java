

/* First created by JCasGen Tue Oct 16 16:38:10 EDT 2012 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Oct 16 18:06:02 EDT 2012
 * XML source: /home/anika/workspace1/hw1-anikag/src/main/resources/TypeSystemDescriptor.xml
 * @generated */
public class NamedEntity extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NamedEntity.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected NamedEntity() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public NamedEntity(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public NamedEntity(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public NamedEntity(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: DocID

  /** getter for DocID - gets 
   * @generated */
  public String getDocID() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_DocID == null)
      jcasType.jcas.throwFeatMissing("DocID", "NamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_DocID);}
    
  /** setter for DocID - sets  
   * @generated */
  public void setDocID(String v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_DocID == null)
      jcasType.jcas.throwFeatMissing("DocID", "NamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_DocID, v);}    
  }

    