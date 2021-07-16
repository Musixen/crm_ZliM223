package ch.zli.m223.ksh18a.crm.controller;

import ch.zli.m223.ksh18a.crm.model.AppCustomer;
import ch.zli.m223.ksh18a.crm.model.AppMemo;
import ch.zli.m223.ksh18a.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;



    @RestController
    public class CustomerController {

        @Autowired
        private CustomerService customerService;

        @RequestMapping(value="/rest/v1/customers", method=RequestMethod.GET)
        public Collection<CustomerDto> showCustomerList() {
            Collection<AppCustomer> customers = customerService.getCustomerList();
            ArrayList<CustomerDto> res = new ArrayList<CustomerDto>();
            customers.forEach(customer -> res.add(new CustomerDto(customer)));
            return res;
        }
        @RequestMapping(value="/rest/v1/customer/create", method=RequestMethod.POST)
        public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) {
            AppCustomer customer =
                    customerService.addCustomer(customerDto.name, customerDto.street, customerDto.city);
            return new CustomerDto(customer);
        }
        @RequestMapping(value="/rest/v1/customer/{id}", method=RequestMethod.GET)
        public CustomerDto showCustomer(@PathVariable("id") long customerId) {
            AppCustomer customer = customerService.getCustomer(customerId);
            return new CustomerDto(customer);
        }

        @RequestMapping(value="/rest/v1/customer/update", method=RequestMethod.POST)
        public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto) {
            AppCustomer customer =
                    customerService.updateCustomer(customerDto.id, customerDto.name, customerDto.street, customerDto.city);
            return new CustomerDto(customer);
        }

        @RequestMapping(value="/rest/v1/customer/{id}/delete", method=RequestMethod.POST)
        public void deleteCustomer(@PathVariable("id") long customerId) {
            customerService.deleteCustomer(customerId);
        }

        @RequestMapping(value="/rest/v1/customer/{id}/memos", method=RequestMethod.GET)
        public List<MemoDto> getMemos(@PathVariable("id") long customerId) {
            AppCustomer customer = customerService.getCustomer(customerId);
            List<MemoDto> res = new ArrayList<>();
            customer.getMemos().forEach(memo -> res.add(new MemoDto(memo)));
            return res;
        }

        @RequestMapping(value="/rest/v1/customer/{id}/memo/create", method=RequestMethod.POST)
        public MemoDto createMemo(@PathVariable("id") long customerId, @RequestBody MemoDto memoDto) {
            AppMemo memo = customerService.addMemoToCustomer(customerId, memoDto.noteText);
            return new MemoDto(memo);
        }

        @RequestMapping(value="/rest/v1/customer/memo/{id}", method=RequestMethod.GET)
        public MemoDto getMemo(@PathVariable("id") long memoId) {
            AppMemo memo = customerService.getMemo(memoId);
            return new MemoDto(memo);
        }

        @RequestMapping(value="/rest/v1/customer/memo/update", method=RequestMethod.POST)
        public MemoDto updateMemo(@RequestBody MemoDto memoDto) {
            AppMemo memo = customerService.updateMemo(memoDto.id, memoDto.noteText,
                    memoDto.getCoverage()==null ? new Date() : new Date(memoDto.getCoverage()));
            return new MemoDto(memo);
        }

        @RequestMapping(value="/rest/v1/customer/memo/{id}/delete", method=RequestMethod.POST)
        public void deleteMemo(@PathVariable("id") long memoId) {
            customerService.deleteMemo(memoId);
        }
    }
