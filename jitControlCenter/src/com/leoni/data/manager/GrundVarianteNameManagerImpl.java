package com.leoni.data.manager;

import com.leoni.data.criterion.CriteriaAppender;
import com.leoni.data.criterion.Equal;
import com.leoni.data.models.GrundVarianteName;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hrmi1005
 * Date: 21.7.2014
 * Time: 10:17
 * To change this template use File | Settings | File Templates.
 */
@Service("grundVarianteNameManager")
public class GrundVarianteNameManagerImpl  extends GenericManagerImpl<GrundVarianteName> implements GrundVarianteNameManager {
    public List<GrundVarianteName> findBySumWertigkeit(Integer sumWertigkeit) {
        List<CriteriaAppender> caList = new ArrayList<CriteriaAppender>();
        caList.add(new Equal("sumWertigkeit", sumWertigkeit));
        return find(caList);
    }
}
