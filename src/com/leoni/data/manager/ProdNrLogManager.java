package com.leoni.data.manager;

import com.leoni.data.models.ProdGroup;
import com.leoni.data.models.ProdNrLog;
import com.leoni.data.models.nonDBmodels.HarnessCheckObj;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: hrmi1005
 * Date: 6.6.2014
 * Time: 12:35
 * To change this template use File | Settings | File Templates.
 */
public interface ProdNrLogManager  extends GenericManager<ProdNrLog> {
    public ProdNrLog saveProdNrLog(String prodNr,String kabelsatzKz,String tableId,String testerId,String mode,String logText);
    public List<ProdNrLog> getProdNrLogPersNrAndProdNr(String date, String timeFrom, String timeTo, String persnr);
    public Map<String, Set<String>> getWorkplace(Map<String, Set<String>> prodnrLogMap,String date, String timeFrom, String timeTo);
    public File exportToFile(Map<String, Set<String>> prodnrLogMap, String persnr,String date, String timeFrom, String timeTo);
    public List<ProdNrLog> getProdNrLogProdNrAndKsKz(String prodNr, String ksKz);
    public List<ProdNrLog> getProdNrLogForHarness(String timeFrom/*, String dateFrom*/, String timeTo, String date,
                                                  String workplaceName, String workplaceMode);
    public File exportToFile(List<HarnessCheckObj> harnessCheckObjList);
    List<ProdNrLog>getProdNrLogByDate(String dateString);

}
