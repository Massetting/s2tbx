package org.esa.s2tbx.dataio.s2.l1b;

import com.bc.ceres.core.Assert;
import https.psd_13_sentinel2_eo_esa_int.dico._1_0.pdgs.dimap.A_PRODUCT_INFO;
import https.psd_13_sentinel2_eo_esa_int.dico._1_0.pdgs.dimap.A_PRODUCT_ORGANIZATION;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.esa.s2tbx.dataio.metadata.GenericXmlMetadata;
import org.esa.s2tbx.dataio.metadata.XmlMetadataParser;
import org.esa.s2tbx.dataio.s2.S2BandConstants;
import org.esa.s2tbx.dataio.s2.S2BandInformation;
import org.esa.s2tbx.dataio.s2.S2Metadata;
import org.esa.s2tbx.dataio.s2.S2MetadataType;
import org.esa.s2tbx.dataio.s2.S2SpatialResolution;
import org.esa.s2tbx.dataio.s2.filepatterns.S2DatastripDirFilename;
import org.esa.s2tbx.dataio.s2.filepatterns.S2DatastripFilename;
import org.esa.s2tbx.dataio.s2.filepatterns.S2GranuleDirFilename;
import org.esa.s2tbx.dataio.s2.l1b.filepaterns.S2L1BDatastripFilename;
import org.esa.s2tbx.dataio.s2.l1b.filepaterns.S2L1BGranuleDirFilename;
import org.esa.s2tbx.dataio.s2.l1c.L1cPSD13Constants;
import org.esa.s2tbx.dataio.s2.ortho.filepatterns.S2OrthoDatastripFilename;
import org.esa.s2tbx.dataio.s2.ortho.filepatterns.S2OrthoGranuleDirFilename;
import org.esa.snap.core.datamodel.MetadataElement;
import org.esa.snap.core.util.Guardian;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by obarrile on 07/10/2016.
 */
public class L1bProductMetadataPSD13 extends GenericXmlMetadata implements IL1bProductMetadata {

    private static class L1bProductMetadataPSD13Parser extends XmlMetadataParser<L1bProductMetadataPSD13> {

        public L1bProductMetadataPSD13Parser(Class metadataFileClass) {
            super(metadataFileClass);
            String[] locations = {S2MetadataType.L1B_PRODUCT_SCHEMA_FILE_PATH};
            setSchemaLocations(locations);
        }

        @Override
        protected boolean shouldValidateSchema() {
            return false;
        }
    }



    public static L1bProductMetadataPSD13 create(Path path) throws IOException {
        Assert.notNull(path);
        L1bProductMetadataPSD13 result = null;
        InputStream stream = null;
        try {
            if (Files.exists(path)) {
                stream = Files.newInputStream(path, StandardOpenOption.READ);
                //noinspection unchecked
                L1bProductMetadataPSD13Parser parser = new L1bProductMetadataPSD13Parser(L1bProductMetadataPSD13.class);
                result = parser.parse(stream);
                result.setName("Level-1B_User_Product");
                String metadataProfile = result.getMetadataProfile();
            }
        } catch (Exception e) {
            //Logger.getLogger(GenericXmlMetadata.class.getName()).severe(e.getMessage());
        } finally {
            if (stream != null) try {
                stream.close();
            } catch (IOException e) {
                // swallowed exception
            }
        }
        return result;
    }
    public L1bProductMetadataPSD13(String name) {
        super(name);
    }

    @Override
    public String getFileName() {
        return null;
    }

    @Override
    public String getMetadataProfile() {
        return null;
    }

    @Override
    public S2Metadata.ProductCharacteristics getProductOrganization() {
        S2Metadata.ProductCharacteristics characteristics = new S2Metadata.ProductCharacteristics();
        characteristics.setSpacecraft(getAttributeValue(L1bPSD13Constants.PATH_PRODUCT_METADATA_SPACECRAFT, "Unknown"));
        characteristics.setDatasetProductionDate(getAttributeValue(L1bPSD13Constants.PATH_PRODUCT_METADATA_SENSING_START, "Unknown"));
        characteristics.setProcessingLevel(getAttributeValue(L1bPSD13Constants.PATH_PRODUCT_METADATA_PROCESSING_LEVEL, "Unknown"));

        characteristics.setProductStartTime(getAttributeValue(L1bPSD13Constants.PATH_PRODUCT_METADATA_PRODUCT_START_TIME, "Unknown"));
        characteristics.setProductStopTime(getAttributeValue(L1bPSD13Constants.PATH_PRODUCT_METADATA_PRODUCT_STOP_TIME, "Unknown"));

        List<S2BandInformation> aInfo = new ArrayList<>();


        aInfo.add(L1bMetadataProc.makeSpectralInformation(S2BandConstants.B1, S2SpatialResolution.R60M));
        aInfo.add(L1bMetadataProc.makeSpectralInformation(S2BandConstants.B2, S2SpatialResolution.R10M));
        aInfo.add(L1bMetadataProc.makeSpectralInformation(S2BandConstants.B3, S2SpatialResolution.R10M));
        aInfo.add(L1bMetadataProc.makeSpectralInformation(S2BandConstants.B4, S2SpatialResolution.R10M));
        aInfo.add(L1bMetadataProc.makeSpectralInformation(S2BandConstants.B5, S2SpatialResolution.R20M));
        aInfo.add(L1bMetadataProc.makeSpectralInformation(S2BandConstants.B6, S2SpatialResolution.R20M));
        aInfo.add(L1bMetadataProc.makeSpectralInformation(S2BandConstants.B7, S2SpatialResolution.R20M));
        aInfo.add(L1bMetadataProc.makeSpectralInformation(S2BandConstants.B8, S2SpatialResolution.R10M));
        aInfo.add(L1bMetadataProc.makeSpectralInformation(S2BandConstants.B8A, S2SpatialResolution.R20M));
        aInfo.add(L1bMetadataProc.makeSpectralInformation(S2BandConstants.B9, S2SpatialResolution.R60M));
        aInfo.add(L1bMetadataProc.makeSpectralInformation(S2BandConstants.B10, S2SpatialResolution.R60M));
        aInfo.add(L1bMetadataProc.makeSpectralInformation(S2BandConstants.B11, S2SpatialResolution.R20M));
        aInfo.add(L1bMetadataProc.makeSpectralInformation(S2BandConstants.B12, S2SpatialResolution.R20M));

        int size = aInfo.size();
        characteristics.setBandInformations(aInfo.toArray(new S2BandInformation[size]));

        return characteristics;
    }

    @Override
    public Collection<String> getTiles() {

        String[] granuleList = getAttributeValues(L1bPSD13Constants.PATH_PRODUCT_METADATA_GRANULE_LIST);
        if(granuleList == null) {
            return null;
        }
        return new ArrayList<>(Arrays.asList(granuleList));
    }

    @Override
    public S2DatastripFilename getDatastrip() {
        String[] datastripList = getAttributeValues(L1bPSD13Constants.PATH_PRODUCT_METADATA_DATASTRIP_LIST);
        if(datastripList == null) {
            return null;
        }

        S2DatastripDirFilename dirDatastrip = S2DatastripDirFilename.create(datastripList[0], null);

        S2DatastripFilename datastripFilename = null;
        if (dirDatastrip != null) {
            String fileName = dirDatastrip.getFileName(null);

            if (fileName != null) {
                datastripFilename = S2L1BDatastripFilename.create(fileName);
            }
        }

        return datastripFilename;
    }

    @Override
    public S2DatastripDirFilename getDatastripDir() {

        String[] granuleList = getAttributeValues(L1bPSD13Constants.PATH_PRODUCT_METADATA_GRANULE_LIST);
        String[] datastripList = getAttributeValues(L1bPSD13Constants.PATH_PRODUCT_METADATA_DATASTRIP_LIST);
        if(granuleList == null || datastripList == null) {
            return null;
        }
        S2GranuleDirFilename grafile = S2L1BGranuleDirFilename.create(granuleList[0]);

        S2DatastripDirFilename datastripDirFilename = null;
        if (grafile != null) {
            String fileCategory = grafile.fileCategory;
            String dataStripMetadataFilenameCandidate = datastripList[0];
            datastripDirFilename = S2DatastripDirFilename.create(dataStripMetadataFilenameCandidate, fileCategory);

        }
        return datastripDirFilename;
    }

    @Override
    public MetadataElement getMetadataElement() {
        return rootElement;
    }
}