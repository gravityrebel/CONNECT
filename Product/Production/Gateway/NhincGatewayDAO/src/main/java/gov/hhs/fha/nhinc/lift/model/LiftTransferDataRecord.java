/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.lift.model;

/**
 *
 * @author vvickers
 */
public class LiftTransferDataRecord {

    private Long Id = null;
    private String RequestKeyGuid = null;
    private String TransferState = null;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getRequestKeyGuid() {
        return RequestKeyGuid;
    }

    public void setRequestKeyGuid(String RequestKeyGuid) {
        this.RequestKeyGuid = RequestKeyGuid;
    }

    public String getTransferState() {
        return TransferState;
    }

    public void setTransferState(String TransferState) {
        this.TransferState = TransferState;
    }
}