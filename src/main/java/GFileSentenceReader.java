import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

/**
*  The class is the code-part of the Collection Reader component of the UIMA Framework. The class extends the CollectionReader_ImplBase
* 
* @author      Anika Gupta
* @version     %I%, %G%
* @since       1.0
*/
public class GFileSentenceReader extends CollectionReader_ImplBase{

  public int currentPointer;
  public ArrayList<String> documents;

  /** 
   *Loads the next document into the CAS object.
   *
   * @param casObj    The CAS object is loaded with a new document
   */
  @Override
  public void getNext(CAS casObj) throws IOException, CollectionException {
    // TODO Auto-generated method stub
    JCas jcasObj;
    try {
      jcasObj = casObj.getJCas();
    } catch (CASException e) {
      throw new CollectionException(e);
    }

    String document = documents.get(currentPointer++);
    jcasObj.setDocumentText(document);

  }

  @Override
  
  public void close() throws IOException {
    // TODO Auto-generated method stub
    
  }

  @Override
  /** 
   *Returns the progress of the Collection Reader.
   *
   */
  public Progress[] getProgress() {
    // TODO Auto-generated method stub
    return new Progress[] { new ProgressImpl(currentPointer, documents.size(), Progress.ENTITIES) };
  }

  @Override
  /** 
   *Returns true if there are more documents to be loaded else, false..
   *
   */
  public boolean hasNext() throws IOException, CollectionException {
    // TODO Auto-generated method stub
    return currentPointer<documents.size();
  }
  /** 
   *Loads the list of documents from the input file.
   *
   * @param inputFile    The File object pointing to the location of the input file.
   * @throws ResourceInitializationException 
   */
  public void fillDocuments(File inputFile) throws ResourceInitializationException{
    
    try{
    BufferedReader bufReader = new BufferedReader(new FileReader(inputFile));
    String line = null;
    while((line=bufReader.readLine())!=null){
          documents.add(line);
        }
        bufReader.close();
    }
    catch(IOException e)
    {
      throw new ResourceInitializationException();
    }
     
  }
  /** 
   *Initializes the Collection Reader with inputFile Path and calls the function to load the documents from the file.
   *
   */
  public void initialize() throws ResourceInitializationException {
    
    File inputFile = new File(((String) getConfigParameterValue("InputFile")));
 // Error Handling
    if (!inputFile.exists() || inputFile.isDirectory()) {
      throw new ResourceInitializationException();
    } 
    currentPointer = 0;
    documents = new ArrayList<String>();
    fillDocuments(inputFile);
    }
    
}
