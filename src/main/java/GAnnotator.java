import java.io.File;
import java.io.IOException;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.util.AbstractExternalizable;

/**
*  The class is the code-part of the Annotator component of the UIMA Framework. The class extends the JCasAnnotator_ImplBase
* 
* @author      Anika Gupta
* @since       1.0
*/
public class GAnnotator extends JCasAnnotator_ImplBase{

  File modelFile=null;
  
  /** 
   *Annotates the document associated with the CAS object.
   *
   * @param casObj    The JCAS object which is to be annotated.
   */
  @Override
  public void process(JCas jcasObj) throws AnalysisEngineProcessException {
    
    String documentLine = jcasObj.getDocumentText();
    int indexSpace = documentLine.indexOf(' ');
    String docID = documentLine.substring(0, indexSpace);
    String document = documentLine.substring(indexSpace + 1);
    
    
    Chunker extractor = null;
    try {
      extractor = (Chunker) AbstractExternalizable.readObject(modelFile);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      throw new AnalysisEngineProcessException();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      throw new AnalysisEngineProcessException();
    }
    Chunking entities = extractor.chunk(document);
    for (Chunk ne : entities.chunkSet()) {
      // Annotations
      NamedEntity annotateNE = new NamedEntity(jcasObj);
      annotateNE.setDocID(docID);
      annotateNE.setBegin(docID.length() + ne.start() + 1);
      annotateNE.setEnd(docID.length() + ne.end()+1);
      annotateNE.addToIndexes();
    }
  }

    
 
  /** 
   *Initializes the Annotator class with path of the model file.
   *
   * @param context    UIMA Context.
   */
  @Override
  public void initialize(UimaContext context) throws ResourceInitializationException{
    super.initialize(context);

    String model = (String)context.getConfigParameterValue("TrainedModel"); 
    modelFile = new File(model); 
  }

}
