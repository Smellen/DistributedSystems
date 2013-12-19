
class SearchResult{
   String words; // strings matched for this url
   String[] url;   // url matching search query 
   long frequency; //number of hits for page
   
   public SearchResult(String words,String[] url, long frequency){
	   this.words = words;
	   this.url = url;
	   this.frequency = 0;
   }
}

