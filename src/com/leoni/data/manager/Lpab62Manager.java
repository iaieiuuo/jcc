package com.leoni.data.manager;

import com.leoni.data.models.Lpab62;
import com.leoni.data.models.nonDBmodels.PrnrLpab64Obj;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hrmi1005
 * Date: 9.4.2014
 * Time: 10:21
 * To change this template use File | Settings | File Templates.
 */
public interface Lpab62Manager  extends GenericManager<Lpab62> {
public Lpab62 deleteBegin(Lpab62 harnessToDeleteBeginEsdStatus);
public List<Lpab62> findByProdnrAndKabelsatz (String prodNr, String kabelsatz);
public List<Lpab62> findByProdnrAndCustomer (String prodNr, String customerNumber);
       List<Lpab62> findByProdnrAndCustomerAndKsKz (String prodNr, String customerNumber,String ksKz);
public List<Integer> getPorscheDates();
public List<Integer> getOsnabruckDates();
public Long getAllHarnessesForDate(String date, String ausfuefhrung, String kskz, String kundenNr, String prodGruppe);
public Long getEinlaufForDate(String date, String ausfuefhrung, String kskz, String kundenNr, String prodGruppe);
public Long getAuslaufForDate(String date, String ausfuefhrung, String kskz, String kundenNr, String prodGruppe);
public Long getBdoseForDate(String date, String ausfuefhrung, String kskz, String kundenNr, String prodGruppe);
public Long getFoamForDate(String date, String ausfuefhrung, String kskz, String kundenNr, String prodGruppe);
public Long getElektroForDate(String date, String ausfuefhrung, String kskz, String kundenNr, String prodGruppe);
public Long getPRForDate(String date, String ausfuefhrung, String kskz, String kundenNr, String prodGruppe);
public Long getEsdScrewForDate(String date, String ausfuefhrung, String kskz, String kundenNr, String prodGruppe);
public Long getPhotoWaForDate(String date, String ausfuefhrung, String kskz, String kundenNr, String prodGruppe);
public int addHarnessF(String prodNr, Integer customerNumber, String prodGruppe, String ausfuehrung,
                         Integer lieferDatum, String reihenfNr);
public int addHarnessC(String prodNr, Integer customerNumber, String prodGruppe, String ausfuehrung,
                           Integer lieferDatum, String reihenfNr);
public int addHarnessT(String prodNr, Integer customerNumber, String prodGruppe, String ausfuehrung,
                           Integer lieferDatum, String reihenfNr);
public int addHarnessU(String prodNr, Integer customerNumber, String prodGruppe, String ausfuehrung,
                           Integer lieferDatum, String reihenfNr);
public int addHarnessE(String prodNr, Integer customerNumber, String prodGruppe, String ausfuehrung,
                           Integer lieferDatum, String reihenfNr);
public int addHarnessR(String prodNr, Integer customerNumber, String prodGruppe, String ausfuehrung,
                           Integer lieferDatum, String reihenfNr);
public int addHarnessWithSelectedKs(String prodNr, Integer customerNumber, String prodGruppe, String ausfuehrung,
                                    Integer lieferDatum, String reihenfNr, String ksKz);
public List<Lpab62> getCustomHarnesses();
public List<Lpab62> searchCustomHarnesses(String prodNrSearch, String kabelsatzKzSearch,String kundenNrSearch);
public List<Lpab62> findByLieferDatumKsKz (String ksKz);
public List<Lpab62> findHarness (String statusName,String dateFrom, String dateTo, String ksKz);
       List<PrnrLpab64Obj> getPrnrLpab64Obj(String sachNrBest);
       List<PrnrLpab64Obj> getPrnrWoLpab64Obj(String sachNrBest);

}
