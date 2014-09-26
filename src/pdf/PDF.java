package pdf;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSBoolean;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSInteger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSNull;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.cos.ICOSVisitor;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdfparser.PDFParser;

public class PDF
{
	class MyCOSVisitor implements ICOSVisitor
	{
		Set<COSBase> visited = new HashSet<COSBase>();
		
		@Override
		public Object visitFromArray(COSArray arg0) throws COSVisitorException
		{
			HashMap<String, Integer> tmp = new HashMap<String, Integer>();
			tmp.put("", 1);
			if (visited.contains(arg0))
				return tmp;
			visited.add(arg0);
			HashMap<String, Integer> ret = new HashMap<String, Integer>();
			for (COSBase object : arg0)
			{
				HashMap<String, Integer> map = (HashMap<String, Integer>) object.accept(this);
				for (String string : map.keySet())
				{
					if (ret.containsKey(string))
						ret.put(string, map.get(string) + ret.get(string));
					else 
						ret.put(string, map.get(string));
				}
			}
			return ret;
		}

		@Override
		public Object visitFromBoolean(COSBoolean arg0) throws COSVisitorException
		{
			HashMap<String, Integer> tmp = new HashMap<String, Integer>();
			tmp.put("", 1);
			if (visited.contains(arg0))
				return tmp;
			visited.add(arg0);
			HashMap<String, Integer> ret = new HashMap<String, Integer>();
			ret.put("", 1);
			return ret;
		}

		@Override
		public Object visitFromDictionary(COSDictionary arg0) throws COSVisitorException
		{
			HashMap<String, Integer> tmp = new HashMap<String, Integer>();
			tmp.put("", 1);
			if (visited.contains(arg0))
				return tmp;
			visited.add(arg0);
			HashMap<String, Integer> ret = new HashMap<String, Integer>();
			ArrayList<COSName> keysList = new ArrayList<COSName>();
			keysList.addAll(arg0.keySet());
			Comparator<COSName> c = new Comparator<COSName>()
			{
				MyCOSVisitor visitor = new MyCOSVisitor();
				@Override
				public int compare(COSName o1, COSName o2)
				{
					try
					{
						HashMap<String, Integer> map1;
						map1 = (HashMap<String, Integer>) o1.accept(visitor);
						String string1 = (String) map1.keySet().toArray()[0];
						HashMap<String, Integer> map2;
						map2 = (HashMap<String, Integer>) o2.accept(visitor);
						String string2 = (String) map2.keySet().toArray()[0];
						return string1.compareTo(string2);
					} 
					catch (COSVisitorException e)
					{
						e.printStackTrace();
					}
					return 0;
				}
			};
			Collections.sort(keysList, c);
			
			for (COSName object : keysList)
			{
				HashMap<String, Integer> map = (HashMap<String, Integer>) object.accept(this); 
				String string = (String) map.keySet().toArray()[0];
				COSBase value = arg0.getItem(object);
				HashMap<String, Integer> valueMap = (HashMap<String, Integer>) value.accept(this);
				for (String valueString : valueMap.keySet())
				{
					String st = "";
					if (string != "") 
						st += "/" + string;
					if (valueString.startsWith("/"))
						st += valueString;
					int count = valueMap.get(valueString);
					if (ret.containsKey(st))
						ret.put(st, count + ret.get(st));
					else 
						ret.put(st, count);
				}
			}
			return ret;
		}

		@Override
		public Object visitFromDocument(COSDocument arg0) throws COSVisitorException
		{
			HashMap<String, Integer> tmp = new HashMap<String, Integer>();
			tmp.put("", 1);
			if (visited.contains(arg0))
				return tmp;
			visited.add(arg0);
			
			HashMap<String, Integer> ret = new HashMap<String, Integer>();
			for (COSObject object : arg0.getObjects())
			{
				HashMap<String, Integer> map = (HashMap<String, Integer>) object.accept(this);
				for (String string : map.keySet())
					if (ret.containsKey(string))
						ret.put(string, map.get(string) + ret.get(string));
					else 
						ret.put(string, map.get(string));
			}
			return ret;
		}

		@Override
		public Object visitFromFloat(COSFloat arg0) throws COSVisitorException
		{
			HashMap<String, Integer> tmp = new HashMap<String, Integer>();
			tmp.put("", 1);
			if (visited.contains(arg0))
				return tmp;
			visited.add(arg0);
			HashMap<String, Integer> ret = new HashMap<String, Integer>();
			ret.put("", 1);
			return ret;
		}

		@Override
		public Object visitFromInt(COSInteger arg0) throws COSVisitorException
		{
			HashMap<String, Integer> tmp = new HashMap<String, Integer>();
			tmp.put("", 1);
			if (visited.contains(arg0))
				return tmp;
			visited.add(arg0);
			HashMap<String, Integer> ret = new HashMap<String, Integer>();
			ret.put("", 1);
			return ret;
		}

		@Override
		public Object visitFromName(COSName arg0) throws COSVisitorException
		{
			HashMap<String, Integer> tmp = new HashMap<String, Integer>();
			tmp.put("", 1);
			if (visited.contains(arg0))
				return tmp;
			visited.add(arg0);
			HashMap<String, Integer> ret = new HashMap<String, Integer>();
			ret.put(arg0.getName(), 1);
			return ret;
		}

		@Override
		public Object visitFromNull(COSNull arg0) throws COSVisitorException
		{
			HashMap<String, Integer> tmp = new HashMap<String, Integer>();
			tmp.put("", 1);
			if (visited.contains(arg0))
				return tmp;
			visited.add(arg0);
			HashMap<String, Integer> ret = new HashMap<String, Integer>();
			ret.put("", 1);
			return ret;
		}

		@Override
		public Object visitFromStream(COSStream arg0) throws COSVisitorException
		{
			HashMap<String, Integer> tmp = new HashMap<String, Integer>();
			tmp.put("", 1);
			if (visited.contains(arg0))
				return tmp;
			visited.add(arg0);
			HashMap<String, Integer> ret = new HashMap<String, Integer>();
			ret.put("", 1);
			return ret;
		}

		@Override
		public Object visitFromString(COSString arg0) throws COSVisitorException
		{
			HashMap<String, Integer> tmp = new HashMap<String, Integer>();
			tmp.put("", 1);
			if (visited.contains(arg0))
				return tmp;
			visited.add(arg0);
			HashMap<String, Integer> ret = new HashMap<String, Integer>();
			ret.put("", 1);
			return ret;
		}
		
	};
	
	private String path;
	
	public PDF(String path)
	{
		this.path = path;
	}
	
	public PDF(File file)
	{
		this.path = file.getPath();
	}
	
	public String getPath()
	{
		return path;
	}
	
	public void setPath(String path)
	{
		this.path = path;
	}
	
	public HashMap<String, Integer> getSetOfPaths()
	{
		HashMap<String, Integer> retMap = null;
		try
		{
			PDFParser parser = new PDFParser(new FileInputStream(new File(path)), null, true);
			parser.parse();
			COSDocument document = parser.getDocument();
			MyCOSVisitor visitor = new MyCOSVisitor();
			retMap = (HashMap<String, Integer>) document.accept(visitor);
			retMap.remove("");
			document.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if (retMap == null) 
			return new HashMap<String, Integer>();
		return retMap;
	}
	
}
