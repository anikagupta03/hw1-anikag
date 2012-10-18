import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

/**
*  The class is the code-part of the Consumer component of the UIMA Framework. The class extends the CasConsumer_ImplBase.
* 
* @author      Anika Gupta
* @since       1.0
*/
public class GConsumer extends CasConsumer_ImplBase{
  BufferedWriter outputWriter = null;
  /** 
   *Counts the number of non white spaces for the start index.
   *
   * @param document    document where the span is found.
   * @param docIDOffset length of the document ID.
   * @param spanBegin   offset of the beginning of the Named Entity
   * @return       Count of the number of non-white spaces for the beginning span.
   */ 
  public int getStartingIndex(String document,int docIDOffset,int spanBegin)
  {
    String span = document.substring(docIDOffset + 1, spanBegin);
    int count = 0;
    for (int i = 0; i < span.length(); i++) {
      if (span.charAt(i) != ' ')
        count++;
    }
    return count;
  }
  /** 
   *Counts the number of non white spaces for the end index.
   *
   * @param document    document where the span is found.
   * @param docIDLength length of the document ID.
   * @param spanEnd   offset of the end of the Named Entity
   * @return       Count of the number of non-white spaces for the end span.
   */ 
  public int getEndingIndex(String document,int docIDLength,int spanEnd){
  String span = document.substring(docIDLength + 1, spanEnd);
  int count = 0;
  for (int i = 0; i < span.length(); i++) {
    if (span.charAt(i) != ' ')
      count++;
  }
  return count;
  }
          
  /** 
   *Writes the annotations of the CAS object into the file.
   *
   * @param casObj    CAS object holding the annotation
   */
  @Override
  public void processCas(CAS casObj) throws ResourceProcessException {
    // TODO Auto-generated method stub
    JCas jcasObj;
    try {
      jcasObj = casObj.getJCas();
    } catch (CASException e) {
      throw new ResourceProcessException(e);
    }
    Iterator iter = jcasObj.getAnnotationIndex(NamedEntity.type).iterator();
    while (iter.hasNext()) {
      NamedEntity ne = (NamedEntity) iter.next();

      
      String document = ne.getCAS().getDocumentText();
      String span = ne.getCoveredText();
      String docID = ne.getDocID();
      
      try {
        int startInd = getStartingIndex(document,docID.length(), ne.getBegin());
        int endInd = getEndingIndex(document,docID.length(), ne.getEnd()-1);
        outputWriter.write(docID + "|" + startInd + " "+ endInd + "|" + span);
        outputWriter.newLine();
        outputWriter.flush();
      } catch (IOException e) {
        throw new ResourceProcessException(e);
      }
    }
    
  }
 

  /** 
   *Initializes the Consumer with handle for the output file.
   *
   */
  public void initialize() throws ResourceInitializationException{
    String outputPath = (String) getUimaContext().getConfigParameterValue("OutputFile");

    if (outputPath == null) {
      throw new ResourceInitializationException(
          ResourceInitializationException.CONFIG_SETTING_ABSENT,
          new Object[] { "OutputFilePath" });
    }

    File outputFile = new File(outputPath);
    if (outputFile.getParentFile() != null
        && !outputFile.getParentFile().exists()) {
        throw new ResourceInitializationException(
            ResourceInitializationException.RESOURCE_DATA_NOT_VALID,
            new Object[] { outputPath, "OutputFilePath" });
    }
    try {
      outputWriter = new BufferedWriter(new FileWriter(outputFile));
    } catch (IOException e) {
      throw new ResourceInitializationException(e);
    }
  }

}
