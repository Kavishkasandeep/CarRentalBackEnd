
package com.Gasto.CarRentalManagement.Services.ServiceIMPL;

import com.Gasto.CarRentalManagement.Const.CommonMsg;
import com.Gasto.CarRentalManagement.Const.CommonStatus;
import com.Gasto.CarRentalManagement.DTO.InvoiceDto;
import com.Gasto.CarRentalManagement.Entity.InvoiceEntity;
import com.Gasto.CarRentalManagement.Repository.InvoiceRepository;
import com.Gasto.CarRentalManagement.Services.InvoiceService;
import com.Gasto.CarRentalManagement.Util.CommonResponse;
import com.Gasto.CarRentalManagement.Util.CommonValidation;
import com.Gasto.CarRentalManagement.Util.DtoToEntityCast;
import com.Gasto.CarRentalManagement.Util.EntityToDtoCast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

    @Service
    public class InvoiceServiceIMPL implements InvoiceService {

        private final InvoiceRepository invoiceRepository;

        @Autowired
        public InvoiceServiceIMPL(InvoiceRepository invoiceRepository) {
            this.invoiceRepository = invoiceRepository;
        }

        @Override
        public CommonResponse saveInvoice(InvoiceDto invoiceDto) {
            CommonResponse commonResponse = new CommonResponse();
            InvoiceEntity invoiceEntity;
            List<String> validationList;

            try {
                validationList = this.invoiceValidation(invoiceDto);

                if (!validationList.isEmpty()) {
                    commonResponse.setErrorMessages(validationList);
                    commonResponse.setCommonMessage("Invoice save failed.");
                    return commonResponse;
                }

                invoiceEntity = DtoToEntityCast.castInvoiceDtoToInvoiceEntity(invoiceDto);
                invoiceEntity = invoiceRepository.save(invoiceEntity);
                commonResponse.setPayload(Collections.singletonList(invoiceEntity));
                commonResponse.setCommonMessage("Invoice saved successfully.");
                commonResponse.setStatus(true);

            } catch (Exception e) {
                LOGGER.error("/**************Exception in Invoice Service -> saveInvoice(^)!", e);
                commonResponse.setCommonMessage("An error occurred while saving the invoice.");
                commonResponse.setStatus(false);
            }
            return commonResponse;
        }

        @Override
        public CommonResponse updateInvoice(InvoiceDto invoiceDto) {
            CommonResponse commonResponse = new CommonResponse();
            List<String> validationList;

            try {
                validationList = this.invoiceValidation(invoiceDto);

                if (!validationList.isEmpty()) {
                    commonResponse.setErrorMessages(validationList);
                    commonResponse.setCommonMessage("Invoice update failed.");
                    return commonResponse;
                }

                Optional<InvoiceEntity> optionalInvoiceEntity = invoiceRepository.findById(invoiceDto.getInvoice_id());
                if (optionalInvoiceEntity.isPresent()) {
                    InvoiceEntity updatedInvoiceEntity = DtoToEntityCast.castInvoiceDtoToInvoiceEntity(invoiceDto);
                    invoiceRepository.save(updatedInvoiceEntity);
                    commonResponse.setPayload(Collections.singletonList(updatedInvoiceEntity));
                    commonResponse.setCommonMessage("Invoice updated successfully.");
                    commonResponse.setStatus(true);
                } else {
                    commonResponse.setCommonMessage("Invoice not found.");
                    commonResponse.setStatus(false);
                }
            } catch (Exception e) {
                LOGGER.error("/**************Exception in Invoice Service -> updateInvoice(^)!", e);
                commonResponse.setCommonMessage("An error occurred while updating the invoice.");
                commonResponse.setStatus(false);
            }
            return commonResponse;
        }

        @Override
        public CommonResponse deleteInvoice(Long invoiceId) {
            CommonResponse commonResponse = new CommonResponse();
            List<String> validationList = this.existingInvoiceValidation(invoiceId);

            try {
                if (!validationList.isEmpty()) {
                    commonResponse.setErrorMessages(validationList);
                    commonResponse.setCommonMessage("Invoice deletion failed.");
                    return commonResponse;
                }

                Optional<InvoiceEntity> optionalInvoiceEntity = invoiceRepository.findById(invoiceId);

                if (optionalInvoiceEntity.isPresent()) {
                    InvoiceEntity invoiceEntity = optionalInvoiceEntity.get();
                    invoiceEntity.setCommon_states(CommonStatus.DELETE);
                    invoiceRepository.save(invoiceEntity);
                    commonResponse.setPayload(Collections.singletonList(invoiceEntity));
                    commonResponse.setCommonMessage("Invoice deleted successfully.");
                    commonResponse.setStatus(true);
                } else {
                    commonResponse.setCommonMessage("Invoice not found.");
                    commonResponse.setStatus(false);
                }
            } catch (Exception e) {
                LOGGER.error("Exception in Invoice Service -> deleteInvoice!", e);
                commonResponse.setCommonMessage("An error occurred while deleting the invoice.");
                commonResponse.setStatus(false);
            }
            return commonResponse;
        }

        @Override
        public CommonResponse getAllInvoices() {
            CommonResponse commonResponse = new CommonResponse();
            List<InvoiceDto> invoiceDtoList = new ArrayList<>();

            try {
                Predicate<InvoiceEntity> filterOnStatus = invoice -> invoice.getCommon_states() != CommonStatus.DELETE;
                invoiceDtoList = invoiceRepository.findAll()
                        .stream()
                        .filter(filterOnStatus)
                        .map(EntityToDtoCast::castInvoiceEntityToInvoiceDto)
                        .collect(Collectors.toList());

                if (invoiceDtoList.isEmpty()) {
                    commonResponse.setCommonMessage("Invoice list is empty.");
                    return commonResponse;
                }

                commonResponse.setPayload(Collections.singletonList(invoiceDtoList));
                commonResponse.setCommonMessage("Invoice list retrieved successfully.");
                commonResponse.setStatus(true);
            } catch (Exception e) {
                LOGGER.error("Exception in Invoice Service -> getAllInvoices!", e);
                commonResponse.setCommonMessage("An error occurred while retrieving the invoice list.");
                commonResponse.setStatus(false);
            }
            return commonResponse;
        }

        @Override
        public CommonResponse getInvoice(Long invoiceId) {
            CommonResponse commonResponse = new CommonResponse();
            List<String> validationList = new ArrayList<>();
            InvoiceEntity existingInvoice;

            try {
                validationList = this.existingInvoiceValidation(invoiceId);

                if (!validationList.isEmpty()) {
                    commonResponse.setErrorMessages(validationList);
                    commonResponse.setCommonMessage("Get invoice failed.");
                    return commonResponse;
                }

                Optional<InvoiceEntity> optionalInvoiceEntity = invoiceRepository.findById(invoiceId);
                if (optionalInvoiceEntity.isPresent()) {
                    InvoiceEntity invoiceEntity = optionalInvoiceEntity.get();
                    InvoiceDto invoiceDto = EntityToDtoCast.castInvoiceEntityToInvoiceDto(invoiceEntity);
                    commonResponse.setPayload(Collections.singletonList(invoiceDto));
                    commonResponse.setCommonMessage("Invoice retrieved successfully.");
                    commonResponse.setStatus(true);
                } else {
                    commonResponse.setCommonMessage("Invoice not found.");
                }
            } catch (Exception e) {
                LOGGER.error("Exception in Invoice Service -> getInvoice!", e);
                commonResponse.setCommonMessage("An error occurred while retrieving the invoice.");
                commonResponse.setStatus(false);
            }
            return commonResponse;
        }

        private List<String> invoiceValidation(InvoiceDto invoiceDto) {
            List<String> validationList = new ArrayList<>();

            if (CommonValidation.iDNullValidation(invoiceDto.getInvoice_id())) {
                validationList.add(CommonMsg.EMPTY_ID);
            }
            if (CommonValidation.iDNullValidation(invoiceDto.getBooking_id())) {
                validationList.add(CommonMsg.EMPTY_ID);
            }
            if (CommonValidation.commenStatusNullValidation(invoiceDto.getCommon_states())) {
                validationList.add(CommonMsg.EMPTY_STATUS);
            }
            if (CommonValidation.isValidDoubleNumber(invoiceDto.getPayment_amount())) {
                validationList.add(CommonMsg.EMPTY_PAYMENT);
            }
            if (CommonValidation.isValidDoubleNumber(invoiceDto.getPaid_amount())) {
                validationList.add(CommonMsg.EMPTY_PAYMENT);
            }

            if (CommonValidation.isValidDoubleNumber(invoiceDto.getBalance())) {
                validationList.add(CommonMsg.EMPTY_PAYMENT);
            }


            return validationList;
        }

        private List<String> existingInvoiceValidation(Long invoiceId) {
            List<String> validationList = new ArrayList<>();

            if (invoiceId == null || invoiceId <= 0) {
                validationList.add(CommonMsg.INVALID_ID);
                return validationList;
            }

            Optional<InvoiceEntity> optionalInvoiceEntity = invoiceRepository.findById(invoiceId);
            InvoiceEntity invoiceEntity = optionalInvoiceEntity.orElse(null);

            if (!optionalInvoiceEntity.isPresent()) {
                validationList.add(CommonMsg.NOT_EXISTING_ID);
            }
            if (invoiceEntity != null && (invoiceEntity.getCommon_states() == CommonStatus.INACTIVE || invoiceEntity.getCommon_states() == CommonStatus.DELETE)) {
                validationList.add(CommonMsg.ALLREADY_DELETED);
            }

            return validationList;
        }

}
