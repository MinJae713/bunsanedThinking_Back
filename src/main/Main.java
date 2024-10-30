package main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import constants.EmployeeNums;
import entity.accident.Accident;
import entity.accident.AccidentList;
import entity.accident.AccidentListImpl;
import entity.accidentHistory.AccidentHistory;
import entity.accidentHistory.AccidentHistoryList;
import entity.accidentHistory.AccidentHistoryListImpl;
import entity.compensationDetail.CompensationDetailList;
import entity.compensationDetail.CompensationDetailListImpl;
import entity.complaint.Complaint;
import entity.complaint.ComplaintList;
import entity.complaint.ComplaintListImpl;
import entity.complaint.ComplaintType;
import entity.contract.Contract;
import entity.contract.ContractList;
import entity.contract.ContractListImpl;
import entity.contract.ContractStatus;
import entity.counsel.Counsel;
import entity.counsel.CounselList;
import entity.counsel.CounselListImpl;
import entity.counsel.CounselProcessStatus;
import entity.customer.Customer;
import entity.customer.CustomerList;
import entity.customer.CustomerListImpl;
import entity.customer.Gender;
import entity.department.Department;
import entity.department.DepartmentList;
import entity.department.DepartmentListImpl;
import entity.depositDetail.DepositDetail;
import entity.depositDetail.DepositDetailList;
import entity.depositDetail.DepositDetailListImpl;
import entity.depositDetail.DepositPath;
import entity.diseaseHistory.DiseaseHistory;
import entity.diseaseHistory.DiseaseHistoryList;
import entity.diseaseHistory.DiseaseHistoryListImpl;
import entity.employee.Employee;
import entity.employee.EmployeeList;
import entity.employee.EmployeeListImpl;
import entity.employee.EmployeePosition;
import entity.employee.Sales;
import entity.endorsment.Endorsement;
import entity.endorsment.EndorsementList;
import entity.endorsment.EndorsementListImpl;
import entity.family.Family;
import entity.family.FamilyList;
import entity.family.FamilyListImpl;
import entity.family.RelationshipType;
import entity.insurance.Automobile;
import entity.insurance.Disease;
import entity.insurance.Injury;
import entity.insurance.InjuryType;
import entity.insurance.Insurance;
import entity.insurance.InsuranceType;
import entity.insurance.ServiceType;
import entity.insurance.VehicleType;
import entity.insuranceMoney.InsuranceMoney;
import entity.insuranceMoney.InsuranceMoneyList;
import entity.insuranceMoney.InsuranceMoneyListImpl;
import entity.jointAcquisition.JointAcquisitionList;
import entity.jointAcquisition.JointAcquisitionListImpl;
import entity.loan.Collateral;
import entity.loan.CollateralType;
import entity.loan.FixedDeposit;
import entity.loan.InsuranceContract;
import entity.loan.Loan;
import entity.loan.LoanType;
import entity.officeSupply.OfficeSupply;
import entity.officeSupply.OfficeSupplyList;
import entity.officeSupply.OfficeSupplyListImpl;
import entity.partnerCompany.PartnerCompany;
import entity.partnerCompany.PartnerCompanyList;
import entity.partnerCompany.PartnerCompanyListImpl;
import entity.partnerCompany.PartnerCompanyType;
import entity.paymentDetail.PaymentDetail;
import entity.paymentDetail.PaymentDetailList;
import entity.paymentDetail.PaymentDetailListImpl;
import entity.paymentDetail.PaymentType;
import entity.product.Product;
import entity.product.ProductList;
import entity.product.ProductListImpl;
import entity.recontract.Recontract;
import entity.recontract.RecontractList;
import entity.recontract.RecontractListImpl;
import entity.reinsurance.ReinsuranceList;
import entity.reinsurance.ReinsuranceListImpl;
import entity.report.Report;
import entity.report.ReportList;
import entity.report.ReportListImpl;
import entity.revival.Revival;
import entity.revival.RevivalList;
import entity.revival.RevivalListImpl;
import entity.surgeryHistory.SurgeryHistory;
import entity.surgeryHistory.SurgeryHistoryList;
import entity.surgeryHistory.SurgeryHistoryListImpl;
import entity.temination.Termination;
import entity.temination.TerminationList;
import entity.temination.TerminationListImpl;
import exception.AlreadyProcessedException;
import exception.AlreadyRequestingException;
import exception.DuplicateDepartmentException;
import exception.DuplicateInsuranceException;
import exception.DuplicateLoanException;
import exception.DuplicateOfficeSupplyException;
import exception.DuplicatePartnerCompanyException;
import exception.DuplicateResidentRegistrationNumberException;
import exception.NotExistContractException;
import exception.NotExistException;
import exception.NotExistExpiredContract;
import exception.NotExistMaintainedContract;
import exception.NotExistTerminatedContract;
import model.administrative.AdministrativeModel;
import model.compensation.CompensationModel;
import model.compensationPlanning.CompensationPlanningModel;
import model.contractManagement.ContractManagementModel;
import model.customer.CustomerModel;
import model.customerInformationManagement.CustomerInformationManagementModel;
import model.customerSupport.CustomerSupportModel;
import model.financialAccountant.FinancialAccountantModel;
import model.humanResource.HumanResourceModel;
import model.loanManagement.LoanManagementModel;
import model.managementPlanning.ManagementPlanningModel;
import model.partnerCompany.PartnerCompanyModel;
import model.productManagement.ProductManagementModel;
import model.sales.SalesModel;
import model.underwriting.UnderwritingModel;

public class Main {
	Scanner scanner;
	private String input;
	private int id;
	private int index;

	// contract
	CompensationDetailList compensationDetailList;
	ContractList contractList;
	DepositDetailList depositDetailList;
	InsuranceMoneyList insuranceMoneyList;
	TerminationList terminationList;
	EndorsementList endorsementList;
	RecontractList recontractList;
	RevivalList revivalList;

	// customer
	AccidentHistoryList accidentHistoryList;
	AccidentList accidentList;
	CounselList counselList;
	CustomerList customerList;
	DiseaseHistoryList diseaseHistoryList;
	ReportList reportList;
	SurgeryHistoryList surgeryHistoryList;

	// employee
	ComplaintList complaintList;
	DepartmentList departmentList;
	EmployeeList employeeList;
	FamilyList familyList;
	JointAcquisitionList jointAcquisitionList;
	OfficeSupplyList officeSupplyList;
	PaymentDetailList paymentDetailList;
	ReinsuranceList reinsuranceList;

	// partner
	PartnerCompanyList partnerCompanyList;

	// product
	ProductList productList;
	
	// models(1029추가)
	private CustomerModel customerModel;
	private AdministrativeModel administrativeModel;
	private CompensationModel compensationModel;
	private CompensationPlanningModel compensationPlanningModel;
	private ContractManagementModel contractManagementModel;
	private CustomerInformationManagementModel customerInformationManagementModel;
	private CustomerSupportModel customerSupportModel;
	private FinancialAccountantModel financialAccountantModel;
	private HumanResourceModel humanResourceModel;
	private LoanManagementModel loanManagementModel;
	private ManagementPlanningModel managementPlanningModel;
	private ProductManagementModel  productManagementModel;
	private SalesModel salesModel;
	private UnderwritingModel underwritingModel;
	private PartnerCompanyModel partnerCompanyModel; // 신규 추가!

	public static void main(String[] args) {
		Main main = new Main();
		main.initialize();
		main.run();
		main.finalize();
	}

	public Main() {

		// contract
		compensationDetailList = new CompensationDetailListImpl();
		contractList = new ContractListImpl();
		depositDetailList = new DepositDetailListImpl();
		insuranceMoneyList = new InsuranceMoneyListImpl();
		terminationList = new TerminationListImpl();
		endorsementList = new EndorsementListImpl();
		recontractList = new RecontractListImpl();
		revivalList = new RevivalListImpl();

		// customer
		accidentHistoryList = new AccidentHistoryListImpl();
		accidentList = new AccidentListImpl();
		counselList = new CounselListImpl();
		customerList = new CustomerListImpl();
		diseaseHistoryList = new DiseaseHistoryListImpl();
		reportList = new ReportListImpl();
		surgeryHistoryList = new SurgeryHistoryListImpl();

		// employee
		complaintList = new ComplaintListImpl();
		departmentList = new DepartmentListImpl();
		employeeList = new EmployeeListImpl();
		familyList = new FamilyListImpl();
		jointAcquisitionList = new JointAcquisitionListImpl();
		officeSupplyList = new OfficeSupplyListImpl();
		paymentDetailList = new PaymentDetailListImpl();
		reinsuranceList = new ReinsuranceListImpl();

		// partner
		partnerCompanyList = new PartnerCompanyListImpl();

		// product
		productList = new ProductListImpl();

		// models
		customerModel = new CustomerModel();
		administrativeModel = new AdministrativeModel();
		compensationModel = new CompensationModel();
		compensationPlanningModel = new CompensationPlanningModel();
		contractManagementModel = new ContractManagementModel();
		customerInformationManagementModel = new CustomerInformationManagementModel();
		customerSupportModel = new CustomerSupportModel();
		financialAccountantModel = new FinancialAccountantModel();
		humanResourceModel = new HumanResourceModel();
		loanManagementModel = new LoanManagementModel();
		managementPlanningModel = new ManagementPlanningModel();
		productManagementModel = new ProductManagementModel();
		salesModel = new SalesModel();
		underwritingModel = new UnderwritingModel();
		partnerCompanyModel = new PartnerCompanyModel();
	}

	public void initialize() {
		scanner = new Scanner(System.in);
	}

	public void run() {
		showMenu();
	}

	public void finalize() {
		scanner.close();
		System.exit(0);
	}

	public void showMenu() {
		index = -1;
		while (index != 4) {
			System.out.println("=====================================================");
			System.out.println("신동아화재 어플리케이션 입니다. 실행하려는 어플리케이션 종류를 클릭해주세요.");
			System.out.println("1. 직원 2. 고객 3. 협력업체 4.종료");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					// 미완
					showEmployeeMenu();
					break;
				case 2:
					// model 적용 완
					showCustomerMenu();
					break;
				case 3:
					// model 적용 완
					showPartnerCompanyMenu();
				case 4:
					break;
				default:
					System.out.println("명시된 번호 중에 클릭해주세요.");
				}

			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			}
		}
	}

/////////////////////////////////////////////////////
	private void showPartnerCompanyMenu() {
		index = -1;
		while (index != 2) {
			System.out.println("========================================");
			System.out.println("신동아화재 협력업체 어플리케이션 입니다. 작업을 클릭해주세요.");
			System.out.println("1. 로그인 2. 취소");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					logInPartnerCompany();
					break;
				case 2:
					break;
				default:
					System.out.println("명시된 번호 중에 클릭해주세요.");
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			}
		}
		index = -1;
	}

	private void logInPartnerCompany() {
		PartnerCompany partnerCompany = null;
		while (partnerCompany == null) {
			System.out.print("ID: ");
			try {
				input = scanner.next();
				id = Integer.parseInt(input);
				partnerCompany = partnerCompanyModel.get(partnerCompanyList, id);
				// 여기
				showPartnerCompanyTask((PartnerCompany) partnerCompany);
			} catch (NumberFormatException e) {
				//
			} catch (NotExistException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void showPartnerCompanyTask(PartnerCompany partnerCompany) {
		if (partnerCompany.getPartnerCompanyType() == PartnerCompanyType.DamageAssessmentCompany) {
			showDamageAssessmentCompanyTask(partnerCompany);
		}
	}

	private void showDamageAssessmentCompanyTask(PartnerCompany partnerCompany) {
		int index = 0;
		do {
			System.out.println(partnerCompany.getName() + "업체! 처리하려는 일을 클릭해주세요.");
			System.out.println("1. 손해 예상 금액 입력");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					EstimatedDamageCost(partnerCompany);
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			}
		} while (1 <= index && index <= 1);
	}

	private void EstimatedDamageCost(PartnerCompany partnerCompany) {
		for (Report report : partnerCompanyModel.getAllReportByDamageAssessmentCompanyID(reportList, partnerCompany.getId())) {
			System.out.println("사고 번호: " + report.getId());
		}

		System.out.print("사고의 번호(더블클릭):");
		input = scanner.next();
		id = Integer.parseInt(input);
		Report report;
		try {
			report = reportList.get(id);
		} catch (NotExistException e) {
			System.out.println("해당하는 사고 " + e.getMessage());
			return;
		}
		System.out.print("손해 예상 금액: ");
		try {
			input = scanner.next();
			int estimatedDamageCost = Integer.parseInt(input);
			report.setDamageAssessmentMoney(estimatedDamageCost);
			partnerCompanyModel.update(reportList, report);
			System.out.println("입력되었습니다.");
		} catch (NumberFormatException e) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			return;
		} catch (NotExistException e) {
			System.out.println("해당하는 사고 " + e.getMessage());
			return;
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////Customer task///////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void showCustomerMenu() {
		index = -1;
		while (index != 3) {
			System.out.println("========================================");
			System.out.println("신동아화재 고객 어플리케이션 입니다. 작업을 클릭해주세요.");
			System.out.println("1. 회원가입 2. 로그인 3. 취소");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					signUpCustomer();
					break;
				case 2:
					logInCustomer();
					break;
				case 3:
					break;
				default:
					System.out.println("명시된 번호 중에 클릭해주세요.");
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			}
		}
		index = -1;
	}

	private void signUpCustomer() {
		Customer customer = new Customer();
		boolean finish = false;
		while (!finish) {
			try {
				System.out.print("1. 고객 이름: ");
				input = scanner.next();
				String name = input;
				System.out.print("2. 전화번호: ");
				input = scanner.next();
				String phoneNumber = input;
				System.out.print("3. 직업: ");
				input = scanner.next();
				String job = input;
				System.out.print("4. 나이: ");
				input = scanner.next();
				int age = Integer.parseInt(input);

				Gender gender = null;
				do {
					System.out.println("5. 성별: ");
					System.out.println("1. 남자 2. 여자");
					input = scanner.next();
					switch (Integer.parseInt(input)) {
					case 1:
						gender = Gender.Male;
						break;
					case 2:
						gender = Gender.Female;
						break;
					default:
						return;
					}
				} while (!(1 <= Integer.parseInt(input) && Integer.parseInt(input) <= 2));

				System.out.print("6. 주민등록번호: ");
				input = scanner.next();
				String residentRegistrationNumber = input;
				System.out.print("7. 주소: ");
				input = scanner.next();
				String address = input;
				System.out.print("8. 재산: ");
				input = scanner.next();
				long property = Integer.parseInt(input);

				ArrayList<AccidentHistory> tempAccidentHistoryList = new ArrayList<AccidentHistory>();
				boolean finishInputAccidentHistory = false;
				while (!finishInputAccidentHistory) {
					System.out.println("9. 사고 이력");
					System.out.println("1. 입력 2. 취소");
					input = scanner.next();
					switch (Integer.parseInt(input)) {
					case 1:
						System.out.print("사고 내용:");
						String detailsOfAccident = scanner.next();
						System.out.print("사고날짜(yyyy-MM-dd) :");
						String dateOfAccident = scanner.next();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
						Date date = formatter.parse(dateOfAccident);

						AccidentHistory accidentHistory = new AccidentHistory(detailsOfAccident, date);
						tempAccidentHistoryList.add(accidentHistory);
						break;
					case 2:
						finishInputAccidentHistory = true;
						break;
					default:
						System.out.println("명시된 번호 중에 클릭해주세요.");
						break;
					}
				}

				ArrayList<SurgeryHistory> tempSurgeryHistoryList = new ArrayList<SurgeryHistory>();
				boolean finishSurgeryHistory = false;
				while (!finishSurgeryHistory) {
					System.out.println("10. 수술 이력");
					System.out.println("1. 입력 2. 취소");
					input = scanner.next();
					switch (Integer.parseInt(input)) {
					case 1:
						System.out.print("수술명:");
						String surgeryName = scanner.next();
						System.out.print("병원이름:");
						String hospitalName = scanner.next();
						System.out.print("수술날짜(yyyy-MM-dd) :");
						String dateOfSurgery = scanner.next();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
						Date date = formatter.parse(dateOfSurgery);
						SurgeryHistory surgeryHistory = new SurgeryHistory(surgeryName, hospitalName, date);
						tempSurgeryHistoryList.add(surgeryHistory);
						break;
					case 2:
						finishSurgeryHistory = true;
						break;
					default:
						System.out.println("명시된 번호 중에 클릭해주세요.");
						break;
					}
				}

				ArrayList<DiseaseHistory> tempDiseaseHistoryList = new ArrayList<DiseaseHistory>();
				boolean finishInputDiseaseHistory = false;
				while (!finishInputDiseaseHistory) {
					System.out.println("11. 병력");
					System.out.println("1. 입력 2. 취소");
					input = scanner.next();
					switch (Integer.parseInt(input)) {
					case 1:
						System.out.print("병명:");
						String diseaseName = scanner.next();
						System.out.print("진단일(yyyy-MM-dd) :");
						String dateOfDiagnosis = scanner.next();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
						Date date = formatter.parse(dateOfDiagnosis);
						DiseaseHistory diseaseHistory = new DiseaseHistory(diseaseName, date);
						tempDiseaseHistoryList.add(diseaseHistory);
						break;
					case 2:
						finishInputDiseaseHistory = true;
						break;
					default:
						System.out.println("명시된 번호 중에 클릭해주세요.");
						break;
					}
				}

				System.out.print("12. 은행명: ");
				input = scanner.next();
				String bankName = input;
				System.out.print("13. 계좌 번호: ");
				input = scanner.next();
				String bankAccount = input;

				do {
					System.out.println("1. 확인 2. 취소");
					input = scanner.next();
					index = Integer.parseInt(input);
					switch (index) {
					case 1:
						customer.signUp(name, phoneNumber, job, age, gender, residentRegistrationNumber, address,
								property, tempAccidentHistoryList, tempSurgeryHistoryList, tempDiseaseHistoryList,
								bankName, bankAccount, customerList, accidentHistoryList, surgeryHistoryList,
								diseaseHistoryList);
						System.out.println("회원가입이 완료되었습니다.회원 아이디는 " + customer.getId() + "입니다.");
						finish = true;
						break;
					case 2:
						return;
					default:
						System.out.println("명시된 번호 중에 클릭해주세요.");
						break;
					}
				} while (!(1 <= index && index <= 2));
			} catch (DuplicateResidentRegistrationNumberException duplicateresidentRegistrationNumber) {
				System.out.println(duplicateresidentRegistrationNumber.getMessage());
				return;
			} catch (NumberFormatException numberFormatException) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
				return;
			} catch (ParseException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
				return;
			}
		}
	}

	private void logInCustomer() {
		index = -1;
		Customer customer = null;
		while (customer == null) {
			System.out.println("ID를 입력해주세요.");
			try {
				input = scanner.next();
				if (input.matches("200\\d+")) {
					id = Integer.parseInt(input);
					customer = customerModel.get(customerList, id);
					if (customer != null) {
						showCustomerTask(customer);
					} else {
						System.out.println("정보를 찾을 수 없습니다. 다시 입력해주세요.");
					}
				} else {
					System.out.println("고객 아이디가 올바르지 않습니다. 다시 입력해주세요.");
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			} catch (NotExistException e) {
				System.out.println(e.getMessage());
			}
		}
		index = -1;
	}

	private void showCustomerTask(Customer customer) {
		do {
			System.out.println(customer.getName() + "님! 처리하려는 일을 클릭해주세요.");
			System.out.println("1. 상품 리스트 2. 기가입 보험 관리 3. 사고 신고 4. 민원 신청");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					customerProductList(customer);
					break;
				case 2:
					managementContract(customer);
					break;
				case 3:
					customerViewAccident(customer);
					break;
				case 4:
					customerViewComplaint(customer);
					break;
				default:
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			}
		} while (1 <= index && index <= 4);
		index = -1;
	}

	private void customerViewComplaint(Customer customer) {
		ArrayList<Complaint> complaintList = customerModel.getAllByCustomerId(this.complaintList, customer.getId());
		// 여기
		int index = 0;
		do {
			for (Complaint complaint : complaintList) {
				System.out.println("민원 번호 : " + complaint.getId() + " 민원 종류 : " + complaint.getComplaintType().getName()
						+ " 제목 : " + complaint.getTitle() + " 등록 날짜, 시간 : " + complaint.getPostDate() + " 내용 : "
						+ complaint.getContent() + " 처리 날짜 : " + complaint.getProcessingDate() + " 처리 상태 : "
						+ complaint.getProcessStatus().getText());
			}
			System.out.println("1. 등록 2. 검색 3. 더블 클릭");
			index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				reportComplaint(customer);
				break;
			case 2:
				complaintList = customerSearchComplaint(customer);
				break;
			case 3:
				customerDoubleClickComplaint(customer);
			}
		} while (1 <= index && index <= 2);
	}
	private void reportComplaint(Customer customer) {
		ComplaintType complainType = getComplaintType();
		System.out.println("민원 제목을 입력해주세요.");
		String title = scanner.next();
		System.out.println("민원 내용을 입력해주세요.");
		String content = scanner.next();

		do {
			System.out.println("1. 등록 2. 취소");
			index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				customer.complain(this.complaintList, this.customerList, complainType, title, content);
				System.out.println("신청 되었습니다.");
				break;
			case 2:
				break;
			default:
				System.out.println("명시된 번호를 클릭해주세요.");
			}
		} while (!(1 <= index && index <= 2));

	}
	private ArrayList<Complaint> customerSearchComplaint(Customer customer) {
		ArrayList<Complaint> result = new ArrayList<>();
		System.out.print("ID 검색창 : ");
		try {
			int id = Integer.parseInt(scanner.next());
			result.add(customerModel.get(complaintList, id));
		} catch (NumberFormatException e) {
			//
		} catch (NotExistException e) {
			System.out.println("해당하는 민원 신청 " + e.getMessage());
		}
		return result;
	}
	private void customerDoubleClickComplaint(Customer customer) {
		try {
			System.out.println("민원의 번호(더블클릭): ");
			int id = Integer.parseInt(scanner.next());
			Complaint complaint = customerModel.get(complaintList, id);
			System.out.println("민원 번호 : " + complaint.getId() + " 민원 종류 : " + complaint.getComplaintType().getName()
					+ " 제목 : " + complaint.getTitle() + " 등록 날짜, 시간 : " + complaint.getPostDate() + " 내용 : "
					+ complaint.getContent() + " 담당자 이름 : " + complaint.getEmployeeName() + " 처리 상태 : "
					+ complaint.getProcessStatus().getText() + " 처리 결과 : " + complaint.getResult());
		} catch (NumberFormatException e) {
			//
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
	}
	private ComplaintType getComplaintType() {
		ComplaintType complainType = null;
		do {
			System.out.println("민원 종류 콤보박스");
			System.out.println("1. 서비스 민원 2. 상품 민원 3. 질문 민원 4. 기타 민원");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);
				complainType = switch (index) {
				case 1 -> ComplaintType.Service;
				case 2 -> ComplaintType.Product;
				case 3 -> ComplaintType.Question;
				case 4 -> ComplaintType.Others;
				default -> null;
				};
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			}
		} while (!(1 <= index && index <= 6));
		index = -1;
		return complainType;
	}

	private void customerViewAccident(Customer customer) {
		try {
			if (customerModel.getAutomobileByMember(contractList, id).getContractStatus() == ContractStatus.ContractRequesting) {
				System.out.println("가입된 자동차 보험이 없습니다.");
				return;
			}
		} catch (NotExistContractException e) {
			System.out.println("가입된 자동차 보험이 없습니다.");
			return;
		}
		ArrayList<Accident> accidentList = customerModel.getAllByCustomer(this.accidentList, customer.getId());
		int index = 0;
		do {
			System.out.println("\n=================");
			for (Accident accident : accidentList) {
				System.out.println("사고 번호 : " + accident.getId() + "\n서비스 종류 : " + accident.getServiceType().getName()
						+ " | 사고 날짜 : " + accident.getDate() + " | 고객 이름 : " + accident.getCustomerName() + " | 전화번호 : "
						+ accident.getCustomerPhoneNumber() + " | 처리 상태 : " + accident.getProcessStatus().getName());
			}
			System.out.println("=================\n");
			System.out.println("1. 등록 2. 검색");
			index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				reportAccident(customer);
				accidentList = customerModel.getAllByCustomer(this.accidentList, customer.getId());
				break;
			case 2:
				accidentList = customerSearchAccident(customer);
				break;
			}
		} while (1 <= index && index <= 2);
	}

	private void reportAccident(Customer customer) {
		ServiceType serviceType = getServiceType();
		Date accidentDate = getAccidentDate();
		System.out.println("사고 위치를 입력해주세요");
		String location = scanner.next();
		System.out.println("고객 이름을 입력해주세요");
		String customerName = scanner.next();
		System.out.println("고객 전화번호를 입력해주세요");
		String customerPhoneNumber = scanner.next();

		int index = 0;
		do {
			System.out.println("1. 등록 2. 취소");
			index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				customer.reportAccident(customerName, customerPhoneNumber, accidentDate, location, serviceType,
						accidentList);
				System.out.println("신고되었습니다.");
				return;
			case 2:
				return;
			default:
				System.out.println("명시된 번호중에 클릭해주세요.");
				break;
			}
		} while (!(1 <= index && index <= 2));
	}

	private ArrayList<Accident> customerSearchAccident(Customer customer) {
		ArrayList<Accident> result = new ArrayList<>();
		System.out.print("ID 검색창 : ");
		try {
			int id = Integer.parseInt(scanner.next());
			result.add(customerModel.get(accidentList, id));
		} catch (NumberFormatException e) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
		} catch (NotExistException e) {
			System.out.println("해당하는 사고 신고 " + e.getMessage());
		}
		return result;
	}

	private ServiceType getServiceType() {
		ServiceType serviceType = null;
		do {
			System.out.println("필요한 서비스 종류를 선택해주세요");
			System.out.println("1. 긴급견인 2. 긴급시동 3. 비상급유 4. 배터리충전 5. 엔진과열 수리 6. 타이어펑크 수리");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);
				serviceType = switch (index) {
				case 1 -> ServiceType.EmergencyTowing;
				case 2 -> ServiceType.EmergencyStart;
				case 3 -> ServiceType.EmergencyRefueling;
				case 4 -> ServiceType.BatteryCharging;
				case 5 -> ServiceType.EngineOverheatingRepair;
				case 6 -> ServiceType.TirepunkRepair;
				default -> null;
				};
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			}
		} while (!(1 <= index && index <= 6));
		index = -1;
		return serviceType;
	}

	private Date getAccidentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd.HH:mm");
		Date date = null;
		do {
			System.out.println("사고 날짜와 시간을 입력해주세요. ex) 2020-04-12.22:09");
			try {
				input = scanner.next();
				date = formatter.parse(input);
			} catch (ParseException e) {
				System.out.println("형식에 맞춰 입력해주세요.");
			}
		} while (date == null);
		return date;
	}

	private void customerProductList(Customer customer) {
		int index = 0;
		do {
			System.out.println("1. 보험 상품 리스트 2. 대출 상품 리스트");
			try {
				index = Integer.parseInt(scanner.next());
				switch (index) {
				case 1:
					viewInsuranceList(customer);
					break;
				case 2:
					viewLoanList(customer);
					break;
				}
			} catch (NumberFormatException e) {
				return;
			}
		} while (1 <= index && index <= 2);
	}

	//////////////////////////////
	private void viewInsuranceList(Customer customer) {
		ArrayList<Insurance> insuranceList = customerModel.getAllInsurance(productList);
		int index = 0;
		do {
			System.out.println("\n=================");
			for (Insurance insurance : insuranceList) {
				System.out.println("상품 번호 : " + insurance.getId() + "\n보험 종류 " + insurance.getInsuranceType().getName() 
						+ " | 이름 : " + insurance.getName() + " | 연령대 : " + insurance.getAgeRange() + " | 월 보험료 : "
						+ insurance.getMonthlyPremium());
				System.out.println("-----------------");
			}
			System.out.println("=================\n");
			System.out.println("1. 보험 종류 콤보박스 2. 검색 3. 더블 클릭");
			try {
				index = Integer.parseInt(scanner.next());

				switch (index) {
				case 1:
					insuranceList = getTypeInsuranceList();
					break;
				case 2:
					insuranceList = searchInsurance();
					break;
				case 3:
					customerDoubleClickInsurance(customer, insuranceList);
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			}
		} while (1 <= index && index <= 3);
	}

	private ArrayList<Insurance> getTypeInsuranceList() {
		ArrayList<Insurance> result = customerModel.getAllInsurance(productList);
		System.out.println("1. 질병 2. 상해 3. 자동차");
		try {
			int index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				result = customerModel.getAllDiseaseInsurance(productList);
				break;
			case 2:
				result = customerModel.getAllInjuryInsurance(productList);
				break;
			case 3:
				result = customerModel.getAllAutomobileInsurance(productList);
				break;
			}
		} catch (NumberFormatException e) {
			//
		}
		return result;
	}

	private ArrayList<Insurance> searchInsurance() {
		ArrayList<Insurance> result = new ArrayList<>();
		System.out.print("ID 검색창 : ");
		try {
			int id = Integer.parseInt(scanner.next());
			result.add(customerModel.get(id, productList));
		} catch (NumberFormatException e) {
			//
		} catch (NotExistException e) {
			System.out.println("보험 상품 " + e.getMessage());
		}
		return result;
	}

	private void customerDoubleClickInsurance(Customer customer, ArrayList<Insurance> insuranceList) {
		try {
			System.out.println("보험의 번호(더블클릭): ");
			input = scanner.next();
			id = Integer.parseInt(input);
			Insurance insurance = customerModel.viewInsuranceProductList(this.productList, id);
			System.out.println("\n=================");
			System.out.print("보험 번호 : " + insurance.getId() + "\n보험 상품 이름 : " + insurance.getName() + " | 보험 종류 : "
					+ insurance.getInsuranceType().getName() + " | 연령대 : " + insurance.getAgeRange() + "\n보장 내용 : "
					+ insurance.getCoverage() + " | 월 보험료 : " + insurance.getMonthlyPremium() + "\n계약 기간 : "
					+ insurance.getContractPeriod());
			switch (insurance.getInsuranceType()) {
			case Automobile:
				System.out.print("\n지원 서비스 :");
				for (ServiceType serviceType : ((Automobile) insurance).getServiceList()) {
					System.out.print(" " + serviceType.getName());
				}
				System.out.println("\n차량 종류 : " + ((Automobile) insurance).getVehicleType().getName() + " 최대 사고 횟수 : "
						+ ((Automobile) insurance).getAccidentLimit());
				break;
			case Disease:
				System.out.println("\n병명 : " + ((Disease) insurance).getDiseaseName() + " 최대 질병 횟수 : "
						+ ((Disease) insurance).getDiseaseLimit());
				break;
			case Injury:
				System.out.println("\n상해 정도 : " + ((Injury) insurance).getInjuryType().getName() + " 최대 수술 횟수 : "
						+ ((Injury) insurance).getSurgeriesLimit());
				break;
			}
			System.out.println("=================\n");
			selectInsurance(customer, insurance);
		} catch (NumberFormatException e) {
			//
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
	}

	private void selectInsurance(Customer customer, Insurance insurance) {
		System.out.println("1. 상담 신청 2. 보험 가입 신청");
		try {
			int index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				askInsuranceCounsel(customer, insurance);
				break;
			case 2:
				if (customer.buyInsurance(insurance, this.contractList)) {
					System.out.println("신청되었습니다.");
				}
				break;
			}
		} catch (NumberFormatException e) {
			//
		}
	}

	private Gender getGender() throws IllegalArgumentException {
		System.out.println("성별을 선택해주세요.");
		System.out.println("1. 남성 2. 여성");
		String input = scanner.next();
		int index = Integer.parseInt(input);
		if (index == 1) {
			return Gender.Male;
		} else if (index == 2) {
			return Gender.Female;
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void askInsuranceCounsel(Customer customer, Insurance insurance) {
		try {
			System.out.println("이름 : ");
			String name = scanner.next();
			System.out.println("전화번호 : ");
			String phoneNumber = scanner.next();
			Date counselDate = getCounselDate();
			System.out.println("직업 : ");
			String job = scanner.next();
			System.out.println("나이 : ");
			input = scanner.next();
			int age = Integer.parseInt(input);
			Gender gender = getGender();
			customer.askInsuranceCounsel(insurance, name, phoneNumber, counselDate, job, age, gender, counselList);
			System.out.println("신청되었습니다.");
		} catch (IllegalArgumentException exception) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요");
		}
	}

	private Date getCounselDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		do {
			System.out.println("상담 희망 날짜를 입력해주세요. ex) 2020-04-12");
			try {
				input = scanner.next();
				date = formatter.parse(input);
			} catch (ParseException e) {
				System.out.println("형식에 맞춰 입력해주세요.");
			}
		} while (date == null);
		return date;

	}

	private void viewLoanList(Customer customer) {
		ArrayList<Loan> loanList = customerModel.getAllLoan(productList);
		int index = 0;
		do {
			for (Loan loan : loanList) {
				System.out.println("대출 상품 번호 : " + loan.getId() + " 대출 상품 이름 : " + loan.getName() + " 대출 상품 종류 : "
						+ loan.getLoanType().getName() + " 이자율 : " + loan.getInterestRate() + " 대출 가능 최대 금액 : "
						+ loan.getLimit());
			}
			System.out.println("1. 대출 종류 콤보박스 2. 검색 3. 더블 클릭");
			try {
				index = Integer.parseInt(scanner.next());

				switch (index) {
				case 1:
					loanList = getTypeLoanList();
					break;
				case 2:
					loanList = searchLoan();
					break;
				case 3:
					customerDoubleClickLoan(customer, loanList);
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			}
		} while (1 <= index && index <= 3);
	}

	private ArrayList<Loan> getTypeLoanList() {
		ArrayList<Loan> result = customerModel.getAllLoan(productList);
		System.out.println("1. 담보 2. 정기 예금 3. 보험 계약");
		try {
			int index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				result = customerModel.getAllCollateralLoan(productList);
				break;
			case 2:
				result = customerModel.getAllFixedDepositLoan(productList);
				break;
			case 3:
				result = customerModel.getAllInsuranceContractLoan(productList);
				break;
			}
		} catch (NumberFormatException e) {
			//
		}
		return result;
	}

	private ArrayList<Loan> searchLoan() {
		ArrayList<Loan> result = new ArrayList<>();
		System.out.print("ID 검색창 : ");
		try {
			int id = Integer.parseInt(scanner.next());
			result.add(customerModel.getLoan(productList, id));
		} catch (NumberFormatException e) {
			//
		} catch (NotExistException e) {
			System.out.println("해당하는 대출 상품 정보가 존재하지 않습니다.");
		}
		return result;
	}

	private void customerDoubleClickLoan(Customer customer, ArrayList<Loan> loanList) {
		try {
			System.out.println("대출의 번호(더블클릭): ");
			input = scanner.next();
			id = Integer.parseInt(input);
			Loan loan = customerModel.viewLoanProductList(this.productList, id);
			System.out.println("");
			selectLoan(customer, loan);
		} catch (NumberFormatException e) {
			//
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
	}

	private void selectLoan(Customer customer, Loan loan) {
		System.out.print("대출 상품 번호 : " + loan.getId() + " 대출 상품 이름 : " + loan.getName() + " 대출 상품 종류 : "
				+ loan.getLoanType().getName() + " 이자율 : " + loan.getInterestRate() + " 대출 가능 최대 금액 : "
				+ loan.getLimit());
		switch (loan.getLoanType()) {
		case Collateral:
			System.out.println(" 담보 종류 : " + ((Collateral) loan).getCollateralType().getName() + " 담보 최소 가치 : "
					+ ((Collateral) loan).getMinimumValue());
			break;
		case FixedDeposit:
			System.out.println(" 최소 예치 금액 : " + ((FixedDeposit) loan).getMinimumAmount());
			break;
		case InsuranceContract:
			System.out.println(" 대상 상품 ID : " + ((InsuranceContract) loan).getProductID());
			break;
		}
		System.out.println("1. 대출 가입 신청");
		try {
			int index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				if (customer.loan(loan, this.contractList)) {
					System.out.println("신청되었습니다.");
				}
				break;
			}
		} catch (NumberFormatException e) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
		} catch (AlreadyRequestingException e) {
			System.out.println(e.getMessage());
		}
	}

	private void managementContract(Customer customer) {
		int index = 0;
		ArrayList<Contract> contractList;
		try {
			contractList = customerModel.getAllApprovedByCustomer(this.contractList, customer.getId());
		} catch (NotExistContractException e) {
			System.out.println("기가입 보험 상품 " + e.getMessage());
			return;
		}
		do {
			try {
				System.out.println("\n=================");
				for (Contract contract : contractList) {
					Insurance product = customerModel.getInsurance(productList, contract.getProduct().getId());
					System.out.println("<보험 상품 정보>");
					System.out.println("계약 아이디 : " + contract.getId() + "\n보험 상품 이름 : " + product.getName() + " | 보험 종류 : "
							+ product.getInsuranceType().getName() + " | 한도 : " + product.getLimit() + " | 연령대 : "
							+ product.getAgeRange() + "\n보장 내용 : " + product.getCoverage() + " | 월 보험료 : "
							+ product.getMonthlyPremium() + "\n계약 기간(년): " + product.getContractPeriod() 
							+ " | 만기일 : " + contract.getExpirationDate() + " | 가입일 : " + contract.getDate() 
							+ " | 보험 상태 : " + contract.getContractStatus().getText());
					System.out.println("-----------------");
				}
				System.out.println("=================\n");
				System.out.println("1. 콤보 박스 2. 검색 3. 더블 클릭");
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					contractList = getInsuranceType(customer);
					break;
				case 2:
					contractList = searchInsuranceContract();
					break;
				case 3:
					doubleClickInsuranceContract(customer);
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			} catch (NotExistException | NotExistContractException e) {
				System.out.println(e.getMessage());
			}
		} while (1 <= index && index <= 3);
	}

	private ArrayList<Contract> getInsuranceType(Customer customer) throws NotExistContractException {
		System.out.println("1. 자동차 보험 2. 상해 보험 3. 질병 보험");
		int index = Integer.parseInt(scanner.next());
		ArrayList<Contract> result = customerModel.getAllByCustomer(contractList, customer.getId());
		switch (index) {
		case 1:
			result = customerModel.getAllAutomobileInsuranceContract(contractList);
			break;
		case 2:
			result = customerModel.getAllInjuryInsuranceContract(contractList);
			break;
		case 3:
			result = customerModel.getAllDiseaseInsuranceContract(contractList);
			break;
		}
		return result;
	}

	private ArrayList<Contract> searchInsuranceContract() {
		try {
			ArrayList<Contract> result = new ArrayList<>();
			System.out.println("ID 검색창 : ");
			int id = Integer.parseInt(scanner.next());
			ArrayList<Contract> contract = customerModel.getContractByproductId(contractList, id);
			result.addAll(contract);
			return result;
		} catch (NumberFormatException e) {
			//
		} catch (IllegalArgumentException e) {
			System.out.println("해당 계약 정보가 존재하지 않습니다.");
		}
		return null;
	}

	private void doubleClickInsuranceContract(Customer customer) {
		System.out.println("계약 번호(더블클릭): ");
		input = scanner.next();
		id = Integer.parseInt(input);
		try {
			Contract contract;
			contract = customerModel.get(contractList, id);
			// 여기
			Product product = contract.getProduct();
			if (product instanceof Insurance) {
				Insurance insurance = (Insurance) product;
				System.out.println(" 보험 상품 이름 : " + insurance.getName() + " 보험 상품 번호 : " + insurance.getId() + " 연령대 : "
						+ insurance.getAgeRange() + " 월 보험료 : " + insurance.getMonthlyPremium() + " 만기일 : "
						+ contract.getExpirationDate() + " 가입일 : " + contract.getDate() + " 납부일 : "
						+ contract.getPaymentDate() + " 보험 상태 : " + contract.getContractStatus().getText());
			}
			System.out.println("메뉴를 선택해주세요.");
			System.out.println("1. 만기 재가입 신청 2. 부활 신청 3. 보험 해지 신청 4. 배서 신청 5. 보험료 납부 6. 보험금 신청");
			int index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				customerModel.applyRecontract(this.contractList, this.recontractList, contract, customer);
				System.out.println("신청되었습니다.");
				break;
			case 2:
				customerModel.applyInsuranceRevival(this.contractList, this.revivalList, contract, customer);
				System.out.println("신청되었습니다.");
				break;
			case 3:
				customerModel.applyInsuranceTermination(this.contractList, this.terminationList, contract, customer);
				System.out.println("신청되었습니다.");
				break;
			case 4:
				System.out.println("\t 납부일 : " + contract.getPaymentDate());
				System.out.println("배서 신청할 납부일을 입력해주세요.");
				input = scanner.next();
				index = Integer.parseInt(input);
				customerModel.applyEndorsement(this.contractList, this.endorsementList, index, contract);
				System.out.println("신청되었습니다.");
				break;
			case 5:
				payInsuranceFee(customer, contract);
				break;
			case 6:
				recieveInsurance(customer, contract);
				break;
			}

		} catch (NotExistContractException | NotExistMaintainedContract | NotExistTerminatedContract
				| NotExistExpiredContract e) {
			System.out.println(e.getMessage());
		}
	}

	private void payInsuranceFee(Customer customer, Contract contract) {
		System.out.println("결제 종류 콤보박스");
		System.out.println("1. 현금 2. 계좌이체");
		DepositPath depositPath = null;
		do {
			int index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				depositPath = DepositPath.Cash;
				break;
			case 2:
				depositPath = DepositPath.AccountTransfer;
				break;
			default:
				return;
			}
		} while (depositPath == null);
		System.out.print("입금액 : ");
		int money = Integer.parseInt(scanner.next());
		do {
			System.out.println("1. 확인 2. 취소");
			index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				customerModel.payInsurancefee(customer, contract, money, depositPath, this.depositDetailList);
				System.out.println("납부되었습니다.");
			case 2:
				return;
			default:
				System.out.println("명시된 번호중에 클릭해주세요.");
			}
		} while (!(1 <= index && index <= 2));

	}
	private void recieveInsurance(Customer customer, Contract contract) {
		try {
			System.out.println("진단서 : ");
			String medicalCertificate = scanner.next();
			System.out.println("영수증 : ");
			String receipt = scanner.next();
			System.out.println("신분증 사본 : ");
			String residentRegistrationCard = scanner.next();
			BufferedImage medicalCertificateImage = ImageIO.read(new File(medicalCertificate));
			BufferedImage receiptImage = ImageIO.read(new File(receipt));
			BufferedImage residentRegistrationCardImage = ImageIO.read(new File(residentRegistrationCard));
			customer.receiveInsurance(contract, medicalCertificateImage, receiptImage, residentRegistrationCardImage,
					this.insuranceMoneyList);
			System.out.println("신청되었습니다.");
		} catch (IOException e) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요");
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////Employee task///////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void showEmployeeMenu() {
		index = -1;
		while (index != 2) {
			System.out.println("========================================");
			System.out.println("신동아화재 직원 어플리케이션 입니다. 작업을 클릭해주세요.");
			System.out.println("1. 로그인 2. 취소");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					logInEmployee();
					break;
				case 2:
					break;
				default:
					System.out.println("명시된 번호 중에 클릭해주세요.");
				}
			} catch (NumberFormatException e) {
				//
			}
		}
		index = -1;
	}

	private void logInEmployee() {
		index = -1;
		Employee employee = null;
		while (employee == null) {
			System.out.print("ID: ");
			try {
				input = scanner.next();
				id = Integer.parseInt(input);
//				600110~600230
				employee = humanResourceModel.getEmployee(employeeList, id);
				// 여기
				if (input.length() == 8) id /= 100;
				else if (input.length() == 7) id /= 10;
				String empDefault = EmployeeNums.EMP_DEFAULT;
				if (id == Integer.parseInt(empDefault+EmployeeNums.FINANCIAL_ACCOUNTANT_ACCOUNTANT_SERIAL_NUMBER)) {
					showFinancialAccountantTask(employee); // model 변환 완
				} else if (id == Integer.parseInt(empDefault+EmployeeNums.ADMINISTRATIVE_SERIAL_NUMBER)) {
					showAdministrativeTask(employee); // 미완
				} else if (id == Integer.parseInt(empDefault+EmployeeNums.COMPENSATION_SERIAL_NUMBER)) {
					showCompensationTask(employee); // 미완
				} else if (id == Integer.parseInt(empDefault+EmployeeNums.CUSTOMER_SUPPORT_SERIAL_NUMBER)) {
					showCustomerSupportTask(employee); // 미완
				} else if (id == Integer.parseInt(empDefault+EmployeeNums.MANAGEMENT_PLANNING_SERIAL_NUMBER)) {
					showManagementPlanningTask(employee); // 미완
				} else if (id == Integer.parseInt(empDefault+EmployeeNums.HUMANRESOURCE_SERIAL_NUMBER)) {
					showHumanResourceTask(employee); // 미완
				} else if (id == Integer.parseInt(empDefault+EmployeeNums.CUSTOMER_INFORMATION_MANAGEMENT_SERIAL_NUMBER)) {
					showCustomerInformationManagementTask(employee); // 미완
				} else if (id == Integer.parseInt(empDefault+EmployeeNums.SALES_SERIAL_NUMBER)) {
					showSalesTask((Sales)employee); // 미완
				} else if (id == Integer.parseInt(empDefault+EmployeeNums.PRODUCT_MANAGEMENT_SERIAL_NUMBER)) {
					showProductManagementTask(employee); // 미완
				} else if (id == Integer.parseInt(empDefault+EmployeeNums.UNDERWRITING_SERIAL_NUMBER)) {
					showUnderwritingTask(employee); // 미완
				} else if (id == Integer.parseInt(empDefault+EmployeeNums.COMPENSATION_PLANNING_SERIAL_NUMBER)) {
					showCompensationPlanningTask(employee); // 미완
				} else if (id == Integer.parseInt(empDefault+EmployeeNums.CONTRACT_MANAGEMENT_SERIAL_NUMBER)) {
					showContractManagementTask(employee); // 미완
				} else if (id == Integer.parseInt(empDefault+EmployeeNums.LOAN_MANAGEMENT_SERIAL_NUMBER)) {
					showLoanManagementTask(employee); // 미완
				} else {
					System.out.println("정보를 찾을 수 없습니다. 다시 입력해주세요.");
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			} catch (NotExistException e) {
				System.out.println(e.getMessage());
			}
		}
		index = -1;
	}
	// TODO FinancialAccountant
	private void showFinancialAccountantTask(Employee employee) {

		boolean finish = false;
		while (!finish) {
			System.out.println(employee.getName() + "님! 처리하려는 일을 클릭해주세요.");
			System.out.println("1. 세금 납부 내역 조회 2. 지급 사항 처리 3. 입금 사항 조회");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					viewTaxPaymentDetail();
					break;
				case 2:
					viewPaymentDetail(employee);
					break;
				case 3:
					viewDepositDetail(employee);
					break;
				default:
					finish = true;
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			}
		}
		index = -1;
	}

	private void viewTaxPaymentDetail() {
		System.out.println("View Tax Payment Detail");
	}

	private void viewPaymentDetail(Employee employee) {
		int index = 0;
		ArrayList<PaymentDetail> paymentDetailList = financialAccountantModel.getAllPaymentDetail(this.paymentDetailList);
		do {
			try {
				System.out.println("\n=================");
				for (PaymentDetail paymentDetail : paymentDetailList) {
					Contract contract = financialAccountantModel.get(contractList, paymentDetail.getContractId());
					Customer customer = financialAccountantModel.get(customerList, contract.getCustomerID());
					System.out.println("ID : " + paymentDetail.getId() + "\n지급 금액 : " + paymentDetail.getMoney()
							+ " | 지급 대상 ID : " + customer.getId() + " | 은행 이름 : " + paymentDetail.getBank() + " | 예금주 : "
							+ paymentDetail.getAccountHolder() + " | 지급 계좌 번호 : " + paymentDetail.getBankAccount()
							+ "\n지급 형태 : " + paymentDetail.getPaymentType().getName() + " 지급 상태 : "
							+ paymentDetail.getProcessStatus().getName());
					System.out.println("-----------------");
				}
				System.out.println("=================\n");
				System.out.println("1. 지급 상태 콤보박스 2. 검색 3. 더블 클릭");
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					paymentDetailList = getStatusPaymentDetail(employee);
					break;
				case 2:
					paymentDetailList = searchPaymentDetail(employee);
					break;
				case 3:
					doubleClickPaymentDetail(employee);
					paymentDetailList = financialAccountantModel.getAllPaymentDetail(this.paymentDetailList);
					break;
				default:
					return;
				}
			} catch (NumberFormatException e) {
				//
			} catch (NotExistContractException | NotExistException e) {
				System.out.println(e.getMessage());
				return;
			}
		} while (1 <= index && index <= 3);
	}
	private ArrayList<PaymentDetail> getStatusPaymentDetail(Employee employee) {
		System.out.println("1. 미지급 2. 지급 완료");
		ArrayList<PaymentDetail> paymentDetailList = financialAccountantModel.getAllPaymentDetail(this.paymentDetailList);
		// 여기
		int index = Integer.parseInt(scanner.next());
		switch (index) {
		case 1:
			paymentDetailList = financialAccountantModel.getAllUnprocessedPaymentDetail(this.paymentDetailList);
			break;
		case 2:
			paymentDetailList = financialAccountantModel.getAllCompletedPaymentDetail(this.paymentDetailList);
			break;
		}
		return paymentDetailList;
	}
	private ArrayList<PaymentDetail> searchPaymentDetail(Employee employee) {
		System.out.print("ID 검색창 : ");
		ArrayList<PaymentDetail> result = new ArrayList<>();
		try {
			int index = Integer.parseInt(scanner.next());
			PaymentDetail paymentDetail = financialAccountantModel.get(paymentDetailList, index);
			result.add(paymentDetail);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	private void doubleClickPaymentDetail(Employee employee) {
		try {
			System.out.print("더블 클릭한 지급 사항의 번호 : ");
			int index = Integer.parseInt(scanner.next());
			PaymentDetail paymentDetail = financialAccountantModel.get(paymentDetailList, index);
			selectPaymentDetail(employee, paymentDetail);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		} catch (NotExistContractException e) {
			System.out.println(e.getMessage());
		}
	}

	private void selectPaymentDetail(Employee employee, PaymentDetail paymentDetail)
			throws NotExistException, NotExistContractException {
		// FinancialAccountantModel employee - 모델
		int index = 0;
		Contract contract = financialAccountantModel.get(contractList, paymentDetail.getContractId());
		Customer customer = financialAccountantModel.get(customerList, contract.getCustomerID());
		System.out.println("\n=================");
		System.out.println("ID : " + paymentDetail.getId() + "\n지급 금액 : " + paymentDetail.getMoney() + " | 지급 대상 ID : "
				+ customer.getId() + " | 은행 이름 : " + paymentDetail.getBank() + " | 예금주 : "
				+ paymentDetail.getAccountHolder() + " | 지급 계좌 번호 : " + paymentDetail.getBankAccount() + "\n지급 형태 : "
				+ paymentDetail.getPaymentType().getName() + " | 지급 상태 : " + paymentDetail.getProcessStatus().getName()
				+ " | 지급 계약 ID : " + contract.getId());
		System.out.println("=================\n");
		do {
			
			System.out.println("1. 지급 2. 취소");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					financialAccountantModel.handlePayment(paymentDetail, paymentDetailList);
					System.out.println("지급되었습니다.");
					return;
				case 2:
					break;
				default:
					System.out.println("명시된 번호 중에 클릭해주세요.");
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			} catch (AlreadyProcessedException e) {
				System.out.println(e.getMessage());
				return;
			}
		} while (index != 2);
	}
	private void viewDepositDetail(Employee employee) {
		int index = 0;
		ArrayList<DepositDetail> depositDetailList = financialAccountantModel.getAllDepositDetail(this.depositDetailList);
		do {
			try {
				for (DepositDetail depositDetail : depositDetailList) {
					Contract contract = financialAccountantModel.get(contractList, depositDetail.getContractId());
					Customer customer = financialAccountantModel.get(customerList, contract.getCustomerID());
					System.out.println("입금 사항 번호 : " + depositDetail.getId() + " 은행 이름 : " + customer.getBankName()
							+ " 계좌 번호 : " + customer.getBankAccount() + " 입금자 이름 :" + depositDetail.getDepositorName()
							+ " 입금 날짜 : " + depositDetail.getDate() + " 입금 금액 : " + depositDetail.getMoney()
							+ " 입금 경로 : " + depositDetail.getPath().getText());
				}
				System.out.println("1. 검색");
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 0:
					break;
				case 1:
					depositDetailList = searchDepositDetail(employee);
					break;
				}
			} catch (NumberFormatException e) {
				//
			} catch (NotExistContractException | NotExistException e) {
				System.out.println(e.getMessage());
				return;
			}
		} while (1 <= index && index <= 1);
	}

	private ArrayList<DepositDetail> searchDepositDetail(Employee employee) {
		// FinancialAccountantModel employee - 모델 접근
		ArrayList<DepositDetail> result = new ArrayList<>();
		try {
			System.out.print("ID 검색창 : ");
			int id = Integer.parseInt(scanner.next());
			DepositDetail depositDetail = financialAccountantModel.getDepositDetail(this.depositDetailList, id);
			result.add(depositDetail);
		} catch (NotExistException e) {
			System.out.println("해당하는 입금 " + e.getMessage());
		}
		return result;
	}
//	2024-06-02 김대현
	// TODO Administrative
	private void showAdministrativeTask(Employee employee) {
		boolean finish = false;
		while (!finish) {
			System.out.println(employee.getName() + "님! 처리하려는 일을 클릭해주세요.");
			System.out.println("1. 집기 비품 재고 관리");
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				manageAdministrative(employee);
				break;
			default:
				finish = true;
				break;
			}
		}
		index = -1;
	}

//	2024-06-02 김대현
	private void manageAdministrative(Employee employee) {
		System.out.println("집기 비품 정보 리스트");

		for (OfficeSupply officeSupply : officeSupplyList.getAll()) {
			// 여기
			System.out.print("비품 번호: " + officeSupply.getId() + " ");
			System.out.print("비품 이름: " + officeSupply.getName() + " ");
			System.out.print("재고 수량: " + officeSupply.getInventory() + " ");
			System.out.println("총 재고 수량: " + officeSupplyList.getTotalInventory() + " ");
		}

		System.out.println("1. 등록 2. 검색 3. 더블클릭");
		try {
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				createOfficeSupply(employee);
				break;
			case 2:
				getOfficeSupply(employee);
				break;
			case 3:
				doubleClickOfficeSupply(employee);
				break;
			default:
				return;
			}
		} catch (NumberFormatException numberFormatException) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			return;
		}

	}

//	2024-06-02 김대현
	private void createOfficeSupply(Employee employee) {
		// AdministrativeModel employee - 모델
		boolean finish = false;
		while (!finish) {
			try {
				System.out.print("1. 비품 이름: ");
				input = scanner.next();
				String name = input;
				System.out.print("2. 비품 설명: ");
				input = scanner.next();
				String explain = input;
				System.out.print("3. 재고 수량: ");
				input = scanner.next();
				int inventory = Integer.parseInt(input);

				do {
					System.out.println("1. 확인 2. 취소");
					input = scanner.next();
					index = Integer.parseInt(input);
					switch (index) {
					case 1:
						administrativeModel.addOfficeSupply(name, explain, inventory, officeSupplyList);
						System.out.println("집기 비품 정보가 등록되었습니다.");
						finish = true;
						break;
					case 2:
						return;
					default:
						System.out.println("명시된 번호중에 클릭해주세요.");
					}
				} while (!(1 <= index && index <= 2));
			} catch (DuplicateOfficeSupplyException dupplicateOfficeSupply) {
				System.out.println(dupplicateOfficeSupply.getMessage());
				return;
			} catch (NumberFormatException numberFormatException) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
				return;
			}
		}

	}

//	2024-06-02 김대현
	private void getOfficeSupply(Employee employee) {
		// AdministrativeModel employee
		System.out.print("검색창: ");
		input = scanner.next();
		id = Integer.parseInt(input);
		
		OfficeSupply officeSupply;
		try {
			officeSupply = administrativeModel.getOfficeSupply(officeSupplyList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.print("비품 번호: " + officeSupply.getId() + " ");
		System.out.print("비품 이름: " + officeSupply.getName() + " ");
		System.out.print("재고 수량: " + officeSupply.getInventory() + " ");
		System.out.println("총 재고 수량: " + officeSupplyList.getTotalInventory() + " ");
	}

//	2024-06-02 김대현
	private void doubleClickOfficeSupply(Employee employee) {
		// AdministrativeModel employee
		System.out.println("집기 비품의 번호(더블클릭): ");
		input = scanner.next();
		id = Integer.parseInt(input);

		OfficeSupply officeSupply;
		
		try {
			officeSupply = administrativeModel.getOfficeSupply(officeSupplyList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.print("1. 비품 번호: " + officeSupply.getId() + " ");
		System.out.print("2. 비품 이름: " + officeSupply.getName() + " ");
		System.out.print(
				"3. 재고 수량: " + officeSupply.getInventory() + " ");
		System.out.print("4. 총 재고 수량: " + officeSupplyList.getTotalInventory() + " ");
		System.out.println("5. 비품 설명: " + officeSupply.getExplain());

		System.out.println("1. 수정 2. 삭제");
		input = scanner.next();
		index = Integer.parseInt(input);
		switch (index) {
		case 1:
			updateOfficeSupply(employee, id);
			break;
		case 2:
			deleteOfficeSupply(employee, id);
			break;
		default:
			return;
		}

	}

	private void updateOfficeSupply(Employee employee, int id) {
		// AdministrativeModel employee
		OfficeSupply officeSupply;
		try {
			officeSupply = (OfficeSupply) administrativeModel.getOfficeSupply(officeSupplyList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}

		System.out.println("비품 번호: " + officeSupply.getId());
		System.out.println("1. 비품 이름: " + officeSupply.getName());
		System.out.println("2. 비품 설명: " + officeSupply.getExplain());
		System.out.println("3. 비품 재고: " + officeSupply.getInventory());

		try {
			int inputIndex = 0;
			input = scanner.next();
			inputIndex = Integer.parseInt(input);
			if (!(1 <= inputIndex && inputIndex <= 3)) {
				return;
			}
			System.out.println(inputIndex + "번에 해당하는 새로운 값: ");
			String inputParameter = scanner.next();

			do {
				System.out.println("1. 수정 2. 취소");
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					do {
						System.out.println("내용을 수정하시겠습니까?");
						System.out.println("1. 확인 2. 취소");
						input = scanner.next();
						index = Integer.parseInt(input);
						switch (index) {
						case 1:
							administrativeModel.updateDepartment(inputIndex, inputParameter, officeSupply, officeSupplyList);
							System.out.println("수정이 완료되었습니다.");
							break;
						case 2:
							return;
						default:
							System.out.println("명시된 번호중에 클릭해주세요.");
						}
					} while (!(1 <= index && index <= 2));
					return;
				case 2:
					return;
				default:
					System.out.println("명시된 번호중에 클릭해주세요.");
				}
			} while (!(1 <= index && index <= 2));

		} catch (DuplicateOfficeSupplyException dupplicateOfficeSupply) {
			System.out.println(dupplicateOfficeSupply.getMessage());
			return;
		} catch (NumberFormatException numberFormatException) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			return;
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}

	}

	private void deleteOfficeSupply(Employee employee, int id) {
		// AdministrativeModel employee
		System.out.println("정말 삭제하시겠습니까?");
		System.out.println("1. 확인 2. 취소");
		input = scanner.next();
		index = Integer.parseInt(input);
		do {
			switch (index) {
			case 1:
				try {
					administrativeModel.deleteOfficeSupply(officeSupplyList, id);
				} catch (NotExistException e) {
					System.out.println(e.getMessage());
					return;
				}
				System.out.println("삭제가 완료되었습니다.");
				return;
			case 2:
				return;
			default:
				System.out.println("명시된 번호중에 클릭해주세요.");
			}
		} while (!(1 <= index && index <= 2));

	}
	// TODO Compensation
	private void showCompensationTask(Employee employee) {

		boolean finish = false;
		while (!finish) {
			System.out.println(employee.getName() + "님! 처리하려는 일을 클릭해주세요.");
			System.out.println("1. 보상 지급 요청 2.보험금 지급 요청");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					viewReport(employee);
					break;
				case 2:
					viewInsuranceMoney(employee);
					break;
				default:
					finish = true;
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			}
		}
		index = -1;
	}

	private void viewInsuranceMoney(Employee employee) {
		ArrayList<InsuranceMoney> insuranceMoneyList = this.insuranceMoneyList.getAll();
		// 여기
		int index = 0;
		do {
			System.out.println("\n=================");
			for (InsuranceMoney insuranceMoney : insuranceMoneyList) {
				Contract contract;
				Customer customer;
				try {
					contract = this.contractList.get(insuranceMoney.getContractId());
					customer = this.customerList.get(contract.getCustomerID());
				} catch (NotExistException e) {
					System.out.println("고객 " + e.getMessage());
					continue;
				} catch (NotExistContractException e) {
					System.out.println("계약 " + e.getMessage());
					continue;
				}
				System.out.println("신청 번호 : " + insuranceMoney.getId() + "\n계약 상품 종류 : "
						+ ((Insurance) contract.getProduct()).getInsuranceType().getName() + " | 신청 날짜 : "
						+ insuranceMoney.getApplyDate() + " | 고객 이름 : " + customer.getName() + " | 처리 상태 : "
						+ insuranceMoney.getProcessStatus().getName());
				System.out.println("-----------------");
			}
			System.out.println("=================\n");
			System.out.println("1. 처리 상태 콤보박스 2. 검색 3. 더블 클릭");
			index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				insuranceMoneyList = getStatusInsuranceMoneyList();
				break;
			case 2:
				insuranceMoneyList = searchInsuranceMoney();
				break;
			case 3:
				doubleClickInsuranceMoney(employee);
				insuranceMoneyList = this.insuranceMoneyList.getAll();
				break;
			}
		} while (1 <= index && index <= 3);
	}

	private ArrayList<InsuranceMoney> getStatusInsuranceMoneyList() {
		ArrayList<InsuranceMoney> result = this.insuranceMoneyList.getAll();
		// 여기
		System.out.println("1. 미처리 2. 처리 완료");
		int index = Integer.parseInt(scanner.next());
		switch (index) {
		case 1:
			result = this.insuranceMoneyList.getAllUnprocessed();
			break;
		case 2:
			result = this.insuranceMoneyList.getAllProcessed();
			break;
		}
		return result;
	}

	private ArrayList<InsuranceMoney> searchInsuranceMoney() {
		ArrayList<InsuranceMoney> result = new ArrayList<>();
		try {
			System.out.println("ID 검색창 : ");
			int index = Integer.parseInt(scanner.next());
			InsuranceMoney insuranceMoney = this.insuranceMoneyList.get(index);
			// 여기
			result.add(insuranceMoney);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	private void doubleClickInsuranceMoney(Employee employee) {
		try {
			System.out.println("클릭한 보험금 신청 정보의 번호 : ");
			int index = Integer.parseInt(scanner.next());
			InsuranceMoney insuranceMoney = this.insuranceMoneyList.get(index);
			// 여기
			selectInsuranceMoney(employee, insuranceMoney);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
	}

	private void selectInsuranceMoney(Employee employee, InsuranceMoney insuranceMoney) {
		// CompensationModel employee
		Contract contract;
		Customer customer;
		try {
			contract = this.contractList.get(insuranceMoney.getContractId());
			// 여기
			customer = this.customerList.get(contract.getCustomerID());
			// 여기
		} catch (NotExistException e) {
			System.out.println("고객 " + e.getMessage());
			return;
		} catch (NotExistContractException e) {
			System.out.println("계약 " + e.getMessage());
			return;
		}
		System.out.println("\n=================");
		System.out.println("신청 번호 : " + insuranceMoney.getId() + "\n계약 상품 종류 : "
				+ ((Insurance) contract.getProduct()).getInsuranceType().getName() + " | 신청 날짜 : "
				+ insuranceMoney.getApplyDate() + " | 고객 이름 : " + customer.getName() + " | 처리 상태 : "
				+ insuranceMoney.getProcessStatus().getName());
		System.out.println("=================\n");
		Image medical = insuranceMoney.getMedicalCertificate();
		medical = medical.getScaledInstance(500, 700, Image.SCALE_SMOOTH);
		JFrame medicalFrame = new JFrame();
		medicalFrame.setSize(500, 700);
		medicalFrame.setLocation(600, 0);
		medicalFrame.add(new ImagePanel(medical));
		medicalFrame.setAlwaysOnTop(true);
		medicalFrame.setVisible(true);
		Image recipt = insuranceMoney.getReceipt();
		recipt = recipt.getScaledInstance(500, 700, Image.SCALE_SMOOTH);
		JFrame reciptFrame = new JFrame();
		reciptFrame.setSize(500, 700);
		reciptFrame.setLocation(300, 0);
		reciptFrame.add(new ImagePanel(recipt));
		reciptFrame.setAlwaysOnTop(true);
		reciptFrame.setVisible(true);
		Image register = insuranceMoney.getResidentRegistrationCard();
		register = register.getScaledInstance(700, 500, Image.SCALE_SMOOTH);
		JFrame registerFrame = new JFrame();
		registerFrame.setSize(700, 500);
		registerFrame.setLocation(0, 0);
		registerFrame.add(new ImagePanel(register));
		registerFrame.setAlwaysOnTop(true);
		registerFrame.setVisible(true);
		System.out.println("1. 요청 2. 취소");
		int index = 0;
		do {
			try {
				index = Integer.parseInt(scanner.next());
				switch (index) {
				case 1:
					System.out.println("지급 금액 : ");
					int money = Integer.parseInt(scanner.next());
					PaymentType paymentType = getPaymentType();
					System.out.println("1. 확인 2. 취소");
					index = Integer.parseInt(scanner.next());
					switch(index) {
					case 1:
						compensationModel.requestInsuranceMoney(customer, money, insuranceMoney, this.insuranceMoneyList,
								paymentType, contract.getId(), this.paymentDetailList);
						return;
					case 2:
						return;
					default:
						System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요");
					}
				case 2:
					break;
				}
			} catch (NumberFormatException e) {
				//
			} catch (NotExistException e) {
				System.out.println(e.getMessage());
				return;
			} catch (AlreadyProcessedException e) {
				System.out.println("이미 접수가 완료되었습니다.");
				return;
			}
		} while (index != 2);
	}

	private void viewReport(Employee employee) {
		ArrayList<Report> reportList = this.reportList.getAll();
		// 여기
		int index = 0;
		do {
			System.out.println("\n=================");
			for (Report report : reportList) {
				Accident accident = report.getAccident();
				System.out.println("신고 번호 : " + report.getId() + "\n사고 번호 : " + accident.getId() + "  | 서비스 종류 : "
						+ accident.getServiceType().getName() + "  | 사고 날짜, 시간 : " + accident.getDate() + " | 사고 위치 : "
						+ accident.getLocation() + "\n고객 이름 : " + accident.getCustomerName() + " | 고객 전화번호 : "
						+ accident.getCustomerPhoneNumber() + "\n접수 상태 : " + accident.getProcessStatus().getName()
						+ " | 처리 상태 : " + report.getProcessStatus().getName() + " | 손해 사정 금액 : " + report.getDamageAssessmentMoney());
				System.out.println("-----------------");
			}
			System.out.println("=================\n");
			System.out.println("1. 지급 상태 콤보박스 2. 검색 3. 더블 클릭");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 0:
					break;
				case 1:
					reportList = getStatusCompensation();
					break;
				case 2:
					reportList = searchReport();
					break;
				case 3:
					doubleClickReport(employee);
					break;
				default:
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			}
		} while (1 <= index && index <= 4);
	}

	private void doubleClickReport(Employee employee) {
		try {
			System.out.println("클릭한 신고 정보의 번호 : ");
			int index = Integer.parseInt(scanner.next());
			Report report = this.reportList.get(index);
			// 여기
			selectReport(employee, report);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
	}

	private ArrayList<Report> searchReport() {
		ArrayList<Report> result = new ArrayList<>();
		try {
			System.out.println("ID 검색창 : ");
			int index = Integer.parseInt(scanner.next());
			Report report;
			report = this.reportList.get(index);
			// 여기
			result.add(report);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	private ArrayList<Report> getStatusCompensation() {
		ArrayList<Report> result = this.reportList.getAll();
		// 여기
		System.out.println("1. 미처리 2. 처리 완료");
		int index = Integer.parseInt(scanner.next());
		switch (index) {
		case 1:
			result = this.reportList.getAllUnprocessedReport();
			break;
		case 2:
			result = this.reportList.getAllCompletedReport();
			break;
		}
		return result;
	}

	private void selectReport(Employee employee, Report report) {
		index = -1;
		Accident accident = report.getAccident();

		System.out.println("\n=================");
		System.out.println("신고 번호 : " + report.getId() + "\n사고 번호 : " + accident.getId() + " | 서비스 종류 : "
				+ accident.getServiceType().getName() + " | 사고 날짜, 시간 : " + accident.getDate() + " | 사고 위치 : "
				+ accident.getLocation() + "\n고객 이름 : " + accident.getCustomerName() + " | 고객 전화번호 : "
				+ accident.getCustomerPhoneNumber() + "\n접수 상태 : " + accident.getProcessStatus().getName()
				+ " | 처리 상태 : " + report.getProcessStatus().getName() + "\n손해 사정 업체 ID : "
				+ report.getDamageAssessmentCompanyID() + " | 긴급서비스 업체 ID : " + report.getRoadsideAssistanceCompanyID()
				+ " | 손해 사정 금액 : " + report.getDamageAssessmentMoney());
		System.out.println("=================\n");
		while (index != 2) {
			System.out.println("1. 요청 2. 취소");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					requestCompensation(employee, report);
					return;
				case 2:
					break;
				default:
					System.out.println("명시된 번호 중에 클릭해주세요.");
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			}
		}
		index = -1;
	}

	private void requestCompensation(Employee employee, Report report) {
		// CompensationModel employee
		Contract contract;
		Customer customer;
		try {
			contract = this.contractList.getAutomobileByMember(report.getAccident().getCustomerID());
			// 여기
			customer = this.customerList.get(report.getAccident().getCustomerID());
		} catch (NotExistContractException | NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		try {
			System.out.println("지급 금액을 입력해주세요");
			input = scanner.next();
			int money = Integer.parseInt(input);
			PaymentType paymentType = getPaymentType();
			compensationModel.requestCompensation(customer.getName(), customer.getBankName(), customer.getBankAccount(), money,
					paymentType, contract.getId(), paymentDetailList, report, reportList, accidentList);
			System.out.println("요청이 완료되었습니다.");
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		} catch (AlreadyProcessedException e) {
			System.out.println("이미 접수가 완료되었습니다.");
			return;
		}
	}

	private PaymentType getPaymentType() {
		PaymentType paymentType = null;
		do {
			System.out.println("필요한 서비스 종류를 선택해주세요");
			System.out.println("1. 현금 2. 계좌아체 ");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);
				paymentType = switch (index) {
				case 1 -> PaymentType.Cash;
				case 2 -> PaymentType.AccountTransfer;
				default -> null;
				};
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			}
		} while (!(1 <= index && index <= 2));
		index = -1;
		return paymentType;
	}
	// TODO CustomerSupport
	private void showCustomerSupportTask(Employee employee) {
		int index = 0;
		boolean finish = false;
		while (!finish) {
			System.out.println(employee.getName() + "님! 처리하려는 일을 클릭해주세요.");
			System.out.println("1. 신고 처리 2. 민원 처리");
			try {
				index = Integer.parseInt(scanner.next());
				switch (index) {
				case 1:
					viewAccident(employee);
					break;
				case 2:
					viewComplaint(employee);
					break;
				default:
					finish = true;
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			}
		}
	}

	private void viewComplaint(Employee employee) {
		int index = 0;
		ArrayList<Complaint> complaintList = this.complaintList.getAll();
		// 여기
		do {
			try {
				for (Complaint complaint : complaintList) {
					Customer customer = this.customerList.get(complaint.getCustomerID());
					System.out.println("민원 번호 : " + complaint.getId() + " 민원 종류 : "
							+ complaint.getComplaintType().getName() + " 제목 : " + complaint.getTitle() + " 등록 날짜, 시간 : "
							+ complaint.getPostDate() + " 담당자 이름 : " + complaint.getEmployeeName() + " 처리된 날짜 : "
							+ complaint.getProcessingDate() + " 처리 상태 : " + complaint.getProcessStatus().getText()
							+ " 접수자 이름 : " + customer.getName() + " 접수자 전화번호 : " + customer.getPhoneNumber());
				}
				System.out.println("1. 처리 상태 콤보박스 2. 검색 3. 더블 클릭");
				index = Integer.parseInt(scanner.next());
				switch (index) {
				case 1:
					complaintList = getStatusComplaint();
					break;
				case 2:
					complaintList = searchComplaint();
					break;
				case 3:
					doubleClickComplaint(employee);
					break;
				}
			} catch (NumberFormatException e) {
				//
			} catch (NotExistException e) {
				System.out.println(e.getMessage());
			}
		} while (1 <= index && index <= 3);
	}

	private ArrayList<Complaint> getStatusComplaint() {
		ArrayList<Complaint> result = this.complaintList.getAll();
		// 여기
		System.out.println("1. 미처리 2. 처리 완료");
		int index = Integer.parseInt(scanner.next());
		switch (index) {
		case 1:
			result = this.complaintList.getAllUnprocessedComplaint();
			break;
		case 2:
			result = this.complaintList.getAllProcessedComplant();
			break;
		}
		return result;
	}

	private ArrayList<Complaint> searchComplaint() {
		ArrayList<Complaint> result = new ArrayList<>();
		try {
			System.out.print("ID 검색창 : ");
			int index = Integer.parseInt(scanner.next());
			Complaint complaint = this.complaintList.get(index);
			// 여기
			result.add(complaint);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	private void doubleClickComplaint(Employee employee) {
		try {
			System.out.print("클릭한 민원 정보의 번호 : ");
			int index = Integer.parseInt(scanner.next());
			Complaint complaint = this.complaintList.get(index);
			// 여기
			selectComplaint(employee, complaint);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
	}

	private void selectComplaint(Employee employee, Complaint complaint) {
		// CustomerSupportModel employee
		Customer customer;
		try {
			customer = this.customerList.get(complaint.getCustomerID());
			// 여기
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("민원 번호 : " + complaint.getId() + " 민원 종류 : " + complaint.getComplaintType().getName()
				+ " 제목 : " + complaint.getContent() + " 처리 상태 : " + complaint.getProcessStatus().getText()
				+ " 접수자 이름 : " + customer.getName() + " 접수자 전화번호 : " + customer.getPhoneNumber());
		System.out.println("1. 접수 2. 취소");
		int index = 0;
		do {
			try {
				index = Integer.parseInt(scanner.next());
				switch (index) {
				case 1:
					System.out.println("처리 결과 : ");
					String input = scanner.nextLine();
					input = scanner.nextLine();
					customerSupportModel.handleComplaint(employee.getName(), complaint, input, this.complaintList);
					return;
				case 2:
					break;
				}
			} catch (NumberFormatException e) {
				//
			} catch (NotExistException | AlreadyProcessedException e) {
				System.out.println(e.getMessage());
				return;
			}
		} while (index != 2);
	}

	private void viewAccident(Employee employee) {
		ArrayList<Accident> accidentList = this.accidentList.getAll();
		// 여기
		do {
			System.out.println("\n=================");
			for (Accident accident : accidentList) {
				System.out.println("ID : " + accident.getId() + " | 서비스 종류 : " + accident.getServiceType().getName()
						+ " \n사고 날짜, 시간 : " + accident.getDate() + " | 사고 위치 : " + accident.getLocation() + "\n고객 이름 : "
						+ accident.getCustomerName() + " | 고객 전화번호 : " + accident.getCustomerPhoneNumber() + "\n접수 상태 : "
						+ accident.getProcessStatus().getName() + " | 고객 ID : " + accident.getCustomerID());
				System.out.println("-----------------");
			}
			System.out.println("=================\n");
			System.out.println("1. 처리 상태 콤보박스 2. 검색 3. 더블 클릭");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 0:
					return;
				case 1:
					accidentList = getStatusAccident();
					break;
				case 2:
					accidentList = searchAccident();
					break;
				case 3:
					doubleClickAccident(employee);
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			}
		} while (1 <= index && index <= 3);
		index = -1;
	}

	private ArrayList<Accident> getStatusAccident() {
		ArrayList<Accident> result = this.accidentList.getAll();
		// 여기
		System.out.println("1. 미처리 2. 처리 완료");
		int index = Integer.parseInt(scanner.next());
		switch (index) {
		case 1:
			result = this.accidentList.getAllUnprocessedReport();
			break;
		case 2:
			result = this.accidentList.getAllCompletedReport();
			break;
		}
		return result;
	}

	private ArrayList<Accident> searchAccident() {
		ArrayList<Accident> result = new ArrayList<>();
		try {
			System.out.print("ID 검색창 : ");
			int index = Integer.parseInt(scanner.next());
			Accident accident;
			accident = this.accidentList.get(index);
			// 여기
			result.add(accident);
		} catch (NotExistException e) {
			e.printStackTrace();
		}
		return result;
	}

	private void doubleClickAccident(Employee employee) {
		try {
			System.out.print("클릭한 사고 정보의 번호 : ");
			int index = Integer.parseInt(scanner.next());
			Accident accident = this.accidentList.get(index);
			// 여기
			selectAccident(employee, accident);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
	}

	private void selectAccident(Employee employee, Accident accident) {
		// CustomerSupportModel employee
		index = -1;
		System.out.println("\n=================");
		System.out.println("ID : " + accident.getId() + " | 서비스 종류 : " + accident.getServiceType().getName()
				+ "\n사고 날짜, 시간 : " + accident.getDate() + " | 사고 위치 : " + accident.getLocation() + "\n고객 이름 : "
				+ accident.getCustomerName() + " | 고객 전화번호 : " + accident.getCustomerPhoneNumber() + "\n접수 상태 : "
				+ accident.getProcessStatus().getName() + " 고객 ID : " + accident.getCustomerID());
		System.out.println("=================\n");
		while (index != 2) {
			System.out.println("1. 접수 2. 취소");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					PartnerCompany damageAssessmentCompany = getDamageAssessmentCompany();
					if (damageAssessmentCompany == null) {
						break;
					}
					PartnerCompany roadsideAssistanceCompany = getRoadAssistanceCompany();
					if (roadsideAssistanceCompany == null) {
						break;
					}
					customerSupportModel.handleAccident(accident, damageAssessmentCompany, roadsideAssistanceCompany,
							this.reportList);
					System.out.println("접수가 완료되었습니다.");
					return;
				case 2:
					break;
				default:
					System.out.println("명시된 번호 중에 클릭해주세요.");
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			} catch (NotExistException | AlreadyProcessedException e) {
				System.out.println(e.getMessage());
			}
		}
		index = -1;
	}

	private PartnerCompany getRoadAssistanceCompany() throws NotExistException {
		ArrayList<PartnerCompany> roadAssistanceCompanyList = this.partnerCompanyList.getAllRoadAssistanceCompany();
		// 여기
		System.out.println("\n=================");
		for (PartnerCompany partnerCompany : roadAssistanceCompanyList) {
			System.out.println("ID : " + partnerCompany.getId() + "\n이름 : " + partnerCompany.getName() + " | 전화번호 : "
					+ partnerCompany.getPhoneNumber());
			System.out.println("-----------------");
		}
		System.out.println("=================\n");
		System.out.println("긴급서비스를 요청할 협력업체의 ID를 입력해주세요.");
		int id = Integer.parseInt(scanner.next());
		return this.partnerCompanyList.getRoadAssistanceCompany(id);
	}

	private PartnerCompany getDamageAssessmentCompany() throws NotExistException {
		ArrayList<PartnerCompany> damageAssessmentCompanyList = this.partnerCompanyList.getAllDamageAssessmentCompany();
		// 여기
		System.out.println("\n=================");
		for (PartnerCompany partnerCompany : damageAssessmentCompanyList) {
			System.out.println("ID : " + partnerCompany.getId() + "\n이름 : " + partnerCompany.getName() + " | 전화번호 : "
					+ partnerCompany.getPhoneNumber());
			System.out.println("-----------------");
		}
		System.out.println("=================\n");
		System.out.println("손해 사정을 요청할 협력업체의 ID를 입력해주세요.");
		int id = Integer.parseInt(scanner.next());
		return this.partnerCompanyList.getDamageAssessmentCompany(id);
	}
	// TODO ManagementPlanning
	private void showManagementPlanningTask(Employee employee) {

		// 600050
		index = -1;
		boolean finish = false;
		while (!finish) {
			System.out.println(employee.getName() + "님! 처리하려는 일을 클릭해주세요.");
			System.out.println("1. 부서 정보 관리");
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				manageDepartment(employee);
				break;
			default:
				finish = true;
				break;
			}
		}
		index = -1;
	}

//	2024-06-02 김대현
	private void manageDepartment(Employee employee) {
		System.out.println("부서 정보 리스트");
		for (Department department : departmentList.getAll()) {
			// 여기
			System.out.print("부서 번호: " + department.getId() + " ");
			System.out.print("부서 이름: " + department.getName() + " ");
			System.out.print("소속 인원: " + department.getEmployeeCount() + " ");
			System.out.println("부서장 이름: " + department.getHeadName() + " ");
		}

		System.out.println("1. 등록 2. 검색 3. 더블클릭");
		try {
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				createDepartment(employee);
				break;
			case 2:
				getDepartment(employee);
				break;
			case 3:
				doubleClickDepartment(employee);
				break;
			default:
				return;
			}
		} catch (Exception e) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
		}
	}

//	2024-06-02 김대현
	private void createDepartment(Employee employee) {
		// ManagementPlanningModel employee
		boolean finish = false;
		while (!finish) {
			try {
				System.out.print("1. 부서 이름: ");
				input = scanner.next();
				String name = input;
				System.out.print("2. 주업무: ");
				input = scanner.next();
				String task = input;
				System.out.print("3. 부서 목적: ");
				input = scanner.next();
				String purpose = input;
				System.out.print("4. 부서장 이름: ");
				input = scanner.next();
				String headName = input;

				do {
					System.out.println("1. 확인 2. 취소");
					input = scanner.next();
					index = Integer.parseInt(input);
					switch (index) {
					case 1:
						managementPlanningModel.addDepartment(name, task, purpose, headName, departmentList);
						System.out.println("부서가 등록되었습니다.");
						finish = true;
						break;
					case 2:
						return;
					default:
						System.out.println("명시된 번호중에 클릭해주세요.");
					}
				} while (!(1 <= index && index <= 2));
			} catch (DuplicateDepartmentException duplicateDepartmentExeption) {
				System.out.println(duplicateDepartmentExeption.getMessage());
				return;
			} catch (NumberFormatException numberFormatException) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
				return;
			}
		}
	}

//	2024-06-02 김대현
	private void getDepartment(Employee employee) {
		// ManagementPlanningModel employee
		System.out.print("검색창: ");
		input = scanner.next();
		id = Integer.parseInt(input);
		Department department;
		
		try {
			department = managementPlanningModel.getDepartment(departmentList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.print("부서 번호: " + department.getId() + " ");
		System.out.print("부서 이름: " + department.getName() + " ");
		System.out.print("소속 인원: " + department.getEmployeeCount() + " ");
		System.out.println("부서장 이름: " + department.getHeadName() + " ");

	}

//	2024-06-02 김대현
	private void doubleClickDepartment(Employee employee) {
		// ManagementPlanningModel employee
		System.out.println("부서의 번호(더블클릭): ");
		input = scanner.next();
		id = Integer.parseInt(input);
		
		Department department;
		
		try {
			department = managementPlanningModel.getDepartment(departmentList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("1. 부서 번호: " + department.getId());
		System.out.println("2. 부서 이름: " + department.getName());
		System.out.println("3. 주업무: " + department.getTask());
		System.out.println("4. 부서 목적: " + department.getPurpose());
		System.out.println("5. 소속 인원: ");
		System.out.println("6. 부서장 이름: " + department.getHeadName());

		System.out.println("1. 수정 2. 삭제");
		input = scanner.next();
		index = Integer.parseInt(input);
		switch (index) {
		case 1:
			updateDepartment(employee, id);
			break;
		case 2:
			deleteDepartment(employee, id);
			break;
		default:
			return;
		}
	}

//	2024-06-02 김대현
//	2024-06-04 김대현
	private void updateDepartment(Employee employee, int id) {
		// ManagementPlanningModel employee
		Department department;
		try {
			department = (Department) managementPlanningModel.getDepartment(departmentList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}

		System.out.println("부서 번호: " + department.getId());

		System.out.println("1. 부서 이름: " + department.getName());
		System.out.println("2. 주업무: " + department.getTask());
		System.out.println("3. 부서 목적: " + department.getPurpose());
		System.out.println("4. 부서장 이름: " + department.getHeadName());

		try {
			int inputIndex = 0;
			input = scanner.next();
			inputIndex = Integer.parseInt(input);
			if (!(1 <= inputIndex && inputIndex <= 4)) {
				return;
			}
			System.out.println(inputIndex + "번에 해당하는 새로운 값: ");
			String inputParameter = scanner.next();

			do {
				System.out.println("1. 수정 2. 취소");
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					do {
						System.out.println("내용을 수정하시겠습니까?");
						System.out.println("1. 확인 2. 취소");
						input = scanner.next();
						index = Integer.parseInt(input);
						switch (index) {
						case 1:
							managementPlanningModel.updateDepartment(inputIndex, 
									inputParameter, department, departmentList);
							System.out.println("수정이 완료되었습니다.");
							break;
						case 2:
							return;
						default:
							System.out.println("명시된 번호중에 클릭해주세요.");
							break;
						}
					} while (!(1 <= index && index <= 2));
					return;
				case 2:
					return;
				default:
					System.out.println("명시된 번호중에 클릭해주세요.");
					break;
				}
			} while (!(1 <= index && index <= 2));

		} catch (DuplicateDepartmentException duplicateDepartment) {
			System.out.println(duplicateDepartment.getMessage());

			return;
		} catch (NumberFormatException numberFormatException) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			return;
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}

	}

//	2024-06-02 김대현
	private void deleteDepartment(Employee employee, int id) {
		// ManagementPlanningModel employee
		do {
			System.out.println("정말로 삭제하시겠습니까?");
			System.out.println("1. 확인 2. 취소");
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				try {
					managementPlanningModel.deleteDepartment(departmentList, id);
				} catch (NotExistException e) {
					System.out.println(e.getMessage());
					return;
				}
				System.out.println("삭제가 완료되었습니다.");
				return;
			case 2:
				return;
			default:
				System.out.println("명시된 번호중에 클릭해주세요.");
			}
		} while (!(1 <= index && index <= 2));

	}

//	2024-06-04 김대현
//	2024-06-06 김대현
	// TODO HumanResource
	private void showHumanResourceTask(Employee employee) {
		// 600160
		index = -1;
		boolean finish = false;
		while (!finish) {
			System.out.println(employee.getName() + "님! 처리하려는 일을 클릭해주세요.");
			System.out.println("1. 추가수당 지급 요청 2.복리후생비용 요청 3.직원 정보 관리");
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				humanResourceModel.requestAdditionalAllowance();
				break;
			case 2:
				humanResourceModel.requestBenefit();
				break;
			case 3:
				manageHumanResource(employee);
				break;
			default:
				finish = true;
				break;
			}
		}
		index = -1;
	}

//	2024-06-04 김대현
//	2024-06-06 김대현
	private void manageHumanResource(Employee employee) {
		System.out.println("직원 정보 리스트");
//		직원 번호, 직원 이름, 직급, 부서 정보(부서 번호), 급여
		for (Employee e : employeeList.getAll()) {
			// 여기
			System.out.print("직원 번호: " + e.getId() + " ");
			System.out.print("직원 이름: " + e.getName() + " ");
			System.out.print("직급: " + e.getPosition() + " ");
			System.out.print("부서 번호: " + e.getDepartmentID() + " ");
			System.out.println("급여: " + e.getSalary());
		}

		System.out.println("1. 등록 2. 검색 3. 더블클릭");
		try {
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				createEmployee(employee);
				break;
			case 2:
				getEmployee(employee);
				break;
			case 3:
				doubleClickEmployee(employee);
				break;
			default:
				return;
			}
		} catch (NumberFormatException numberFormatException) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			return;
		}
	}

//	2024-06-06 김대현
	private void createEmployee(Employee employee) {
		// HumanResourceModel employee
//		직원 이름, 직급, 주소, 전화번호, 계좌번호, 주민등록번호, 은행명, 가족관계, 부서 정보(부서 번호), 급여, 입사 날짜
		boolean finish = false;
		while (!finish) {
			try {

				int teamId = 0;
				System.out.print("소속팀: ");
				System.out.println(
						"1. 재무회계팀 2. 총무관리팀 3. 보상처리팀 4. 고객지원팀 5. 경영관리팀 6. 인사팀 7. 고객정보관리팀 8. 영업팀 9. 상품관리팀 10.U/W팀 11. 보상기획팀 12. 계약관리팀 13. 융자운용팀");
				input = scanner.next();
				switch (Integer.parseInt(input)) {
				case 1:
					teamId = 110;
					break;
				case 2:
					teamId = 120;
					break;
				case 3:
					teamId = 130;
					break;
				case 4:
					teamId = 140;
					break;
				case 5:
					teamId = 150;
					break;
				case 6:
					teamId = 160;
					break;
				case 7:
					teamId = 170;
					break;
				case 8:
					teamId = 180;
					break;
				case 9:
					teamId = 190;
					break;
				case 10:
					teamId = 200;
					break;
				case 11:
					teamId = 210;
					break;
				case 12:
					teamId = 220;
					break;
				case 13:
					teamId = 230;
					break;
				default:
					return;
				}

				System.out.print("1. 직원 이름: ");
				input = scanner.next();
				String name = input;

				EmployeePosition employeePosition = null;
				System.out.println("2. 직급 ");
				System.out.println("1.인턴 2. 사원 3. 주임 4. 과장 5.차장 6. 부장");
				input = scanner.next();
				switch (Integer.parseInt(input)) {
				case 1:
					employeePosition = EmployeePosition.Intern;
					break;
				case 2:
					employeePosition = EmployeePosition.Staff;
					break;
				case 3:
					employeePosition = EmployeePosition.SeniorStaff;
					break;
				case 4:
					employeePosition = EmployeePosition.Manager;
					break;
				case 5:
					employeePosition = EmployeePosition.DeputyGeneralManager;
					break;
				case 6:
					employeePosition = EmployeePosition.GeneralManager;
					break;
				default:
					return;
				}

				System.out.print("3. 주소: ");
				input = scanner.next();
				String address = input;
				System.out.print("4. 전화번호: ");
				input = scanner.next();
				String phoneNumber = input;
				System.out.print("5. 은행명: ");
				input = scanner.next();
				String bankName = input;
				System.out.print("6. 계좌번호: ");
				input = scanner.next();
				String bankAccount = input;
				System.out.print("7. 주민등록번호: ");
				input = scanner.next();
				String residentRegistrationNumber = input;

				ArrayList<Family> tempFamilyList = new ArrayList<Family>();
				boolean finishInputFamily = false;
				while (!finishInputFamily) {
					System.out.println("8. 가족관계");
					System.out.println("1. 입력 2. 취소");
					input = scanner.next();
					switch (Integer.parseInt(input)) {
					case 1:
						RelationshipType relationshipType = null;
						System.out.print("관계:");
						System.out.println("1.엄마 2. 아빠 3. 여형제 4. 남형제 5. 아들 6. 딸");
						input = scanner.next();
						switch (Integer.parseInt(input)) {
						case 1:
							relationshipType = RelationshipType.Mother;
							break;
						case 2:
							relationshipType = RelationshipType.Father;
							break;
						case 3:
							relationshipType = RelationshipType.Sister;
							break;
						case 4:
							relationshipType = RelationshipType.Brother;
							break;
						case 5:
							relationshipType = RelationshipType.Son;
							break;
						case 6:
							relationshipType = RelationshipType.Daughter;
							break;
						default:
							return;
						}

						System.out.print("이름 :");
						String familyName = scanner.next();

						boolean survial = false;
						System.out.print("생존여부 :");
						System.out.println("1.생존 2. 사망");
						input = scanner.next();
						switch (Integer.parseInt(input)) {
						case 1:
							survial = true;
							break;
						case 2:
							survial = false;
							break;
						default:
							return;
						}

						System.out.print("출생년도(yyyy-MM-dd) :");
						String dateOfBirth = scanner.next();
						Date date = null;
						try {
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
							date = formatter.parse(dateOfBirth);
						} catch (ParseException e) {
							System.out.println(e.getMessage());
						}

						Family family = new Family(relationshipType, familyName, survial, date);
						tempFamilyList.add(family);
						break;
					case 2:
						finishInputFamily = true;
						break;
					default:
						System.out.println("명시된 번호 중에 클릭해주세요.");
						break;
					}
				}

				int departmentID;
				do {
					System.out.println("9. 부서 번호");
					for (Department department : departmentList.getAll()) {
						// 여기
						System.out.print(department.getId() + " ");
						System.out.println(department.getName());
					}
					input = scanner.next();
					departmentID = Integer.parseInt(input);
					try {
						if (departmentList.get(departmentID) != null) {
							break;
						}
					} catch (NotExistException e) {
						System.out.println("해당하는 부서 " + e.getMessage());
					}
				} while (true);

				System.out.print("10. 급여: ");
				input = scanner.next();
				int salary = Integer.parseInt(input);
				System.out.print("11. 입사 날짜(yyyy-MM-dd): ");
				input = scanner.next();
				String dateOfEmployment = input;

				do {
					System.out.println("1. 확인 2. 취소");
					input = scanner.next();
					index = Integer.parseInt(input);
					switch (index) {
					case 1:
						humanResourceModel.addEmployee(teamId, name, employeePosition, address, phoneNumber, bankName,
								bankAccount, residentRegistrationNumber, departmentID, salary, dateOfEmployment,
								employeeList, tempFamilyList, familyList);
						System.out.println("등록되었습니다.");
						finish = true;
						break;
					case 2:
						return;
					default:
						System.out.println("명시된 번호중에 클릭해주세요.");
					}
				} while (!(1 <= index && index <= 2));
			} catch (DuplicateResidentRegistrationNumberException duplicateresidentRegistrationNumber) {
				System.out.println(duplicateresidentRegistrationNumber.getMessage());
				return;
			} catch (NumberFormatException numberFormatException) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
				return;
			}
		}

	}

//	2024-06-06 김대현
	private void getEmployee(Employee employee) {
		// HumanResourceModel employee
		System.out.print("검색창: ");
		input = scanner.next();
		id = Integer.parseInt(input);
		Employee rEmployee;
		try {
			rEmployee = humanResourceModel.getEmployee(employeeList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.print("직원 번호: " + rEmployee.getId() + " ");
		System.out.print("직원 이름: " + rEmployee.getName() + " ");
		System.out.print("직급: " + rEmployee.getPosition() + " ");
		System.out.print("부서 번호: " + rEmployee.getDepartmentID() + " ");
		System.out.println("급여: " + rEmployee.getSalary());
	}

//	2024-06-06 김대현
	private void doubleClickEmployee(Employee employee) {
		// HumanResourceModel employee
		System.out.println("직원의 번호(더블클릭): ");
		input = scanner.next();
		id = Integer.parseInt(input);
//		직원 번호 , 직원 이름, 직급, 주소, 전화번호, 은행명, 계좌번호, 주민등록번호, 가족관계, 부서 정보(부서 번호), 급여, 입사 날짜
		Employee rEmployee;
		try {
			rEmployee = humanResourceModel.getEmployee(employeeList, id);
		} catch (NotExistException e) {
			System.out.println("직원 " + e.getMessage());
			return;
		}

		System.out.println("1. 직원 번호: " + rEmployee.getId());
		System.out.println("2. 직원 이름: " + rEmployee.getName());
		System.out.println("3. 직급: " + rEmployee.getPosition());
		System.out.println("4. 주소: " + rEmployee.getAddress());
		System.out.println("5. 전화번호: " + rEmployee.getPhoneNumber());
		System.out.println("6. 은행명: " + rEmployee.getBankName());
		System.out.println("7. 계좌번호: " + rEmployee.getBankAccount());
		System.out.println("8. 주민등록번호: " + rEmployee.getResidentRegistrationNumber());
		System.out.println("9. 가족관계: " + rEmployee.getFamilyList());
		System.out.println("10. 부서 번호: " + rEmployee.getDepartmentID());
		System.out.println("11. 급여: " + rEmployee.getSalary());
		System.out.println("12. 입사 날짜: " + rEmployee.getEmploymentDate());

		System.out.println("1. 수정 2. 삭제");
		input = scanner.next();
		index = Integer.parseInt(input);
		switch (index) {
		case 1:
			updateEmployeeInformation(employee, rEmployee);
			break;
		case 2:
			deleteEmployeeInformation(employee, rEmployee);
			break;
		default:
			return;
		}

	}

//	2024-06-06 김대현
	private void updateEmployeeInformation(Employee employee, Employee rEmployee) {
		// HumanResourceModel employee
//		직원 번호, 주민등록번호, 입사 날짜 텍스트, 직원 상세 정보 입력란(직원 이름, 직급, 주소, 전화번호, 은행명, 계좌번호, 가족관계, 부서 정보(부서 번호), 급여)
		System.out.println("직원 번호: " + rEmployee.getId());
		System.out.println("주민등록번호: " + rEmployee.getResidentRegistrationNumber());
		System.out.println("입사 날짜: " + rEmployee.getEmploymentDate());

		System.out.println("1. 직원 이름: " + rEmployee.getName());
		System.out.println("2. 직급: " + rEmployee.getPosition());
		System.out.println("3. 주소: " + rEmployee.getAddress());
		System.out.println("4. 전화번호: " + rEmployee.getPhoneNumber());
		System.out.println("5. 은행명: " + rEmployee.getBankName());
		System.out.println("6. 계좌번호: " + rEmployee.getBankAccount());
		System.out.println("7. 가족관계: " + rEmployee.getFamilyList());
		System.out.println("8. 부서 번호: " + rEmployee.getDepartmentID());
		System.out.println("9. 급여: " + rEmployee.getSalary());

		try {
			int inputIndex = 0;
			do {
				input = scanner.next();
				inputIndex = Integer.parseInt(input);
				if (!(1 <= inputIndex && inputIndex <= 9)) {
					return;
				} else if (inputIndex == 2) {
					System.out.print("2. 직급 ");
					System.out.println("1.인턴 2. 사원 3. 주임 4. 과장 5.차장 6. 부장");
				} else if (inputIndex == 7) {
//					updateFamilyList(humanResource, id);
				} else if (inputIndex == 8) {

				}
			} while (!(1 <= Integer.parseInt(input) && Integer.parseInt(input) <= 9));

			System.out.println(inputIndex + "번에 해당하는 새로운 값: ");
			String inputParameter = scanner.next();

			if (inputIndex == 2) {
				if (!(1 <= Integer.parseInt(inputParameter) && Integer.parseInt(inputParameter) <= 6)) {
					return;
				}
				;
			}

			do {
				System.out.println("1. 수정 2. 취소");
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					System.out.println("정말로 수정하시겠습니까?");
					System.out.println("1. 확인 2. 취소");
					input = scanner.next();
					index = Integer.parseInt(input);
					switch (index) {
					case 1:
						try {
							humanResourceModel.updateEmployee(inputIndex, inputParameter, rEmployee, employeeList);
							System.out.println("수정되었습니다.");
						} catch (NotExistException e) {
							System.out.println(e.getMessage());
						}
						break;
					case 2:
						return;
					default:
						return;
					}
					return;
				case 2:
					return;
				default:
					System.out.println("명시된 번호중에 클릭해주세요.");
				}
			} while (!(1 <= index && index <= 2));

		} catch (NumberFormatException numberFormatException) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			return;
		}

	}

//	시나리오에 없는 내용
////	2024-06-06 김대현
//	private void updateFamilyList(HumanResource humanResource, int id) {
//		System.out.println("7. 가족관계");
//		System.out.println("1. 입력 2. 수정 3.삭제");
//
//		boolean finish = false;
//		while (!finish) {
//			input = scanner.next();
//			index = Integer.parseInt(input);
//			switch (index) {
//			case 1:
//				addFamily(humanResource, id);
//				break;
//			case 2:
//				updateFamily(humanResource, id);
//				break;
//			case 3:
//				deleteFamily(humanResource, id);
//				break;
//			default:
//				System.out.println("명시된 번호 중에 클릭해주세요.");
//				break;
//			}
//		}
//	}
//
//	// 2024-06-06 김대현
//	private void addFamily(HumanResource humanResource, int id) {
//		boolean finishInputFamily = false;
//		while (!finishInputFamily) {
//			System.out.println("가족관계");
//			System.out.println("1. 입력 2. 취소");
//			input = scanner.next();
//			switch (Integer.parseInt(input)) {
//			case 1:
//				RelationshipType relationshipType = null;
//				do {
//					System.out.print("관계:");
//					System.out.println("1.엄마 2. 아빠 3. 여형제 4. 남형제 5. 아들 6. 딸");
//					input = scanner.next();
//					switch (Integer.parseInt(input)) {
//					case 1:
//						relationshipType = RelationshipType.Mother;
//						break;
//					case 2:
//						relationshipType = RelationshipType.Father;
//						break;
//					case 3:
//						relationshipType = RelationshipType.Sister;
//						break;
//					case 4:
//						relationshipType = RelationshipType.Brother;
//						break;
//					case 5:
//						relationshipType = RelationshipType.Son;
//						break;
//					case 6:
//						relationshipType = RelationshipType.Daughter;
//						break;
//					default:
//						System.out.println("명시된 번호 중에 클릭해주세요.");
//						break;
//					}
//				} while (!(1 <= Integer.parseInt(input) && Integer.parseInt(input) <= 6));
//
//				System.out.print("이름 :");
//				String familyName = scanner.next();
//
//				boolean survial = false;
//				do {
//					System.out.print("생존여부 :");
//					System.out.println("1.생존 2. 사망");
//					input = scanner.next();
//					switch (Integer.parseInt(input)) {
//					case 1:
//						survial = true;
//						break;
//					case 2:
//						survial = false;
//						break;
//					default:
//						System.out.println("명시된 번호 중에 클릭해주세요.");
//						break;
//					}
//				} while (!(1 <= Integer.parseInt(input) && Integer.parseInt(input) <= 2));
//
//				System.out.print("출생년도(yyyy-MM-dd) :");
//				String dateOfBirth = scanner.next();
//				Date date = null;
//				try {
//					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
//					date = formatter.parse(dateOfBirth);
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//
//				Family family = new Family(relationshipType, familyName, survial, date);
//				humanResource.getEmployee(employeeList, id).getFamilyList().add(family);
//				employeeList.update(humanResource);
//				break;
//			case 2:
//				finishInputFamily = true;
//				break;
//			default:
//				System.out.println("명시된 번호 중에 클릭해주세요.");
//				break;
//			}
//		}
//	}
//
//	// 2024-06-06 김대현
//	private void updateFamily(HumanResource humanResource, int id2) {
//
//	}
//
//	// 2024-06-06 김대현
//	private void deleteFamily(HumanResource humanResource, int id) {
//		int count = 0;
//		try {
//			for (Family family : humanResource.getEmployee(employeeList, id).getFamilyList()) {
//				System.out.print(count + " ");
//				System.out.print(family.getId() + " ");
//				System.out.print(family.getName() + " ");
//				System.out.print(family.getRelationship() + " ");
//				System.out.println(family.getBirthDate());
//			}
//			System.out.print("삭제할 가족 ID: ");
//			input = scanner.next();
//			index = Integer.parseInt(input);
//
//			for (Family e : humanResource.getEmployee(employeeList, id).getFamilyList()) {
//				if (e != null && e.getId() == index) {
//					humanResource.getEmployee(employeeList, id).getFamilyList().remove(e);
//					employeeList.update(humanResource);
//					System.out.print("삭제되었습니다.");
//					return;
//				}
//			}
//		} catch (Exception e) {
//			System.out.print("가족 정보가 존재하지 않습니다.");
//		}
//	}

	// 2024-06-06 김대현
	private void deleteEmployeeInformation(Employee employee, Employee rEmployee) {
		// HumanResourceModel employee
		do {
			System.out.println("정말로 삭제하시겠습니까?");
			System.out.println("1. 확인 2. 취소");
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				try {
					humanResourceModel.deleteEmployee(employeeList, rEmployee.getId());
					System.out.println("삭제되었습니다.");
				} catch (NotExistException e) {
					System.out.println(e.getMessage());
				}
				return;
			case 2:
				return;
			default:
				System.out.println("명시된 번호중에서 클릭해주세요.");
			}
		} while (!(1 <= index && index <= 2));

	}

	// 2024-06-04 김대현
	// TODO CustomerInformationManagement
	private void showCustomerInformationManagementTask(Employee employee) {
//		600070
		index = -1;
		boolean finish = false;
		while (!finish) {
			System.out.println(employee.getName() + "님! 처리하려는 일을 클릭해주세요.");
			System.out.println("1. 고객 정보 관리");
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				manageCustomerInformation(employee);
				break;
			default:
				finish = true;
				break;
			}
		}
		index = -1;
	}

//	2024-06-04 김대현
	private void manageCustomerInformation(Employee employee) {
		// CustomerInformationManagementModel employee
//		고객 이름, 전화번호, 직업,나이, 성별, 주민등록번호, 주소, 계좌 번호, 고객 번호

		System.out.println("고객 정보 리스트");
		for (Customer e : customerList.getAll()) {
			// 여기
			System.out.print("고객 번호: " + e.getId() + " ");
			System.out.print("고객 이름: " + e.getName() + " ");
			System.out.print("전화번호: " + e.getPhoneNumber() + " ");
			System.out.print("직업: " + e.getJob() + " ");
			System.out.print("나이: " + e.getAge() + " ");
			System.out.print("성별: " + e.getGender().getName() + " ");
			System.out.print("주민등록번호: " + e.getResidentRegistrationNumber() + " ");
			System.out.print("주소: " + e.getAddress() + " ");
			System.out.println("은행명: " + e.getBankName() + " ");
			System.out.println("계좌 번호: " + e.getBankAccount() + " ");
		}

//		2024-06-04 김대현
		System.out.println("1. 등록 2. 검색 3. 더블클릭");
		try {
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				createCustomerInformation(employee);
				break;
			case 2:
				getCustomerInformation(employee);
				break;
			case 3:
				doubleClickCustomerInformation(employee);
				break;
			default:
				return;
			}
		} catch (NumberFormatException e) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
		}
	}

//	2024-06-04 김대현
	private void createCustomerInformation(Employee employee) {
		// CustomerInformationManagementModel employee
//		고객 이름, 전화번호, 직업, 나이, 성별, 주민번호, 주소, 재산, 사고 이력, 수술 이력, 병력, 은행명 계좌 번호

		boolean finish = false;
		while (!finish) {
			try {
				System.out.print("1. 고객 이름: ");
				input = scanner.next();
				String name = input;
				System.out.print("2. 전화번호: ");
				input = scanner.next();
				String phoneNumber = input;
				System.out.print("3. 직업: ");
				input = scanner.next();
				String job = input;
				System.out.print("4. 나이: ");
				input = scanner.next();
				int age = Integer.parseInt(input);

				Gender gender = null;
				System.out.println("5. 성별: ");
				System.out.println("1. 남자 2. 여자");
				input = scanner.next();
				switch (Integer.parseInt(input)) {
				case 1:
					gender = Gender.Male;
					break;
				case 2:
					gender = Gender.Female;
					break;
				default:
					return;
				}

				System.out.print("6. 주민등록번호: ");
				input = scanner.next();
				String residentRegistrationNumber = input;
				System.out.print("7. 주소: ");
				input = scanner.next();
				String address = input;
				System.out.print("8. 재산: ");
				input = scanner.next();
				long property = Integer.parseInt(input);

				ArrayList<AccidentHistory> tempAccidentHistoryList = new ArrayList<AccidentHistory>();
				boolean finishInputAccidentHistory = false;
				while (!finishInputAccidentHistory) {
					System.out.println("9. 사고 이력 ");
					System.out.println("1. 입력 2. 취소");
					input = scanner.next();
					switch (Integer.parseInt(input)) {
					case 1:
						System.out.print("사고 내용:");
						String detailsOfAccident = scanner.next();
						System.out.print("사고날짜(yyyy-MM-dd) :");
						String dateOfAccident = scanner.next();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
						Date date = formatter.parse(dateOfAccident);
						AccidentHistory accidentHistory = new AccidentHistory(detailsOfAccident, date);
						tempAccidentHistoryList.add(accidentHistory);
						break;
					case 2:
						finishInputAccidentHistory = true;
						break;
					default:
						System.out.println("명시된 번호 중에 클릭해주세요.");
						break;
					}
				}

				ArrayList<SurgeryHistory> tempSurgeryHistoryList = new ArrayList<SurgeryHistory>();
				boolean finishSurgeryHistory = false;
				while (!finishSurgeryHistory) {
					System.out.println("10. 수술 이력");
					System.out.println("1. 입력 2. 취소");
					input = scanner.next();
					switch (Integer.parseInt(input)) {
					case 1:
						System.out.print("수술명:");
						String surgeryName = scanner.next();
						System.out.print("병원이름:");
						String hospitalName = scanner.next();
						System.out.print("수술날짜(yyyy-MM-dd) :");
						String dateOfSurgery = scanner.next();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
						Date date = formatter.parse(dateOfSurgery);
						SurgeryHistory surgeryHistory = new SurgeryHistory(surgeryName, hospitalName, date);
						tempSurgeryHistoryList.add(surgeryHistory);
						break;
					case 2:
						finishSurgeryHistory = true;
						break;
					default:
						System.out.println("명시된 번호 중에 클릭해주세요.");
						break;
					}
				}

				ArrayList<DiseaseHistory> tempDiseaseHistoryList = new ArrayList<DiseaseHistory>();
				boolean finishInputDiseaseHistory = false;
				while (!finishInputDiseaseHistory) {
					System.out.println("11. 병력");
					System.out.println("1. 입력 2. 취소");
					input = scanner.next();
					switch (Integer.parseInt(input)) {
					case 1:
						System.out.print("병명:");
						String diseaseName = scanner.next();
						System.out.print("진단일(yyyy-MM-dd) :");
						String dateOfDiagnosis = scanner.next();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
						Date date = formatter.parse(dateOfDiagnosis);
						DiseaseHistory diseaseHistory = new DiseaseHistory(diseaseName, date);
						tempDiseaseHistoryList.add(diseaseHistory);
						break;
					case 2:
						finishInputDiseaseHistory = true;
						break;
					default:
						System.out.println("명시된 번호 중에 클릭해주세요.");
						break;
					}
				}

				System.out.print("12. 은행명: ");
				input = scanner.next();
				String bankName = input;
				System.out.print("13. 계좌 번호: ");
				input = scanner.next();
				String bankAccount = input;

				do {
					System.out.println("1. 확인 2. 취소");
					input = scanner.next();
					index = Integer.parseInt(input);
					switch (index) {
					case 1:
						customerInformationManagementModel.addCustomerInformation(name, phoneNumber, job, age, gender,
								residentRegistrationNumber, address, property, tempAccidentHistoryList,
								tempSurgeryHistoryList, tempDiseaseHistoryList, bankName, bankAccount, customerList,
								accidentHistoryList, surgeryHistoryList, diseaseHistoryList);
						System.out.println("등록되었습니다.");
						finish = true;
						break;
					case 2:
						return;
					default:
						System.out.println("명시된 번호중에서 클릭해주세요.");
					}
				} while (!(1 <= index && index <= 2));
			} catch (DuplicateResidentRegistrationNumberException e) {
				System.out.println(e.getMessage());
				return;
			} catch (NumberFormatException | ParseException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
				return;
			}
		}

	}

//	2024-06-04 김대현
	private void getCustomerInformation(Employee employee) {
		// CustomerInformationManagementModel employee
		System.out.print("검색창: ");
		input = scanner.next();
		id = Integer.parseInt(input);
		Customer customer;
		try {
			customer = customerInformationManagementModel.getCustomerInformation(customerList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.print("고객 번호: " + customer.getId() + " ");
		System.out.print("고객 이름: " + customer.getName() + " ");
		System.out.print("전화번호: " + customer.getPhoneNumber() + " ");
		System.out.print("직업: " + customer.getJob() + " ");
		System.out.print("나이: " + customer.getAge() + " ");
		System.out.print("성별: " + customer.getGender().getName() + " ");
		System.out.print("주민등록번호: " + customer.getResidentRegistrationNumber() + " ");
		System.out.print("주소: "+ customer.getAddress() + " ");
		System.out.print("은행명: " + customer.getBankName() + " ");
		System.out.println("계좌 번호: " + customer.getBankAccount());
	}

//	2024-06-04 김대현
	private void doubleClickCustomerInformation(Employee employee) {
		// CustomerInformationManagementModel employee
		System.out.println("고객의 번호(더블클릭): ");
		input = scanner.next();
		id = Integer.parseInt(input);
		Customer customer;
		try {
			customer = customerInformationManagementModel.getCustomerInformation(customerList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("1. 고객 번호: " + customer.getId());
		System.out.println("2. 고객 이름: " + customer.getName());
		System.out.println("3. 전화번호: " + customer.getPhoneNumber());
		System.out.println("4. 직업: " + customer.getJob());
		System.out.println("5. 나이: " + customer.getAge());
		System.out.println("6. 성별: " + customer.getGender().getName());
		System.out.println("7. 주민등록번호: " + customer.getResidentRegistrationNumber());
		System.out.println("8. 주소: " + customer.getAddress());
		System.out.println("9. 재산: " + customer.getProperty());
		System.out.println("10. 사고 이력: " + customer.getAccidentHistoryList());
		System.out.println("11. 수술 이력: " + customer.getSurgeryHistoryList());
		System.out.println("12. 병력: " + customer.getDiseaseHistoryList());
		System.out.println("13. 은행명: " + customer.getBankName());
		System.out.println("14. 계좌 번호: " + customer.getBankAccount());

		System.out.println("1. 수정 2. 삭제");
		input = scanner.next();
		index = Integer.parseInt(input);
		switch (index) {
		case 1:
			updateCustomerInformation(employee, id);
			break;
		case 2:
			deleteCustomerInformation(employee, id);
			break;
		default:
			return;
		}
	}

//	2024-06-04 김대현
	private void updateCustomerInformation(Employee employee, int id) {
		// CustomerInformationManagementModel employee
		Customer customer;
		try {
			customer = customerInformationManagementModel.getCustomerInformation(customerList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}

		System.out.println("고객 번호: " + customer.getId());
		System.out.println("주민등록번호: " + customer.getResidentRegistrationNumber());
		System.out.println("1. 고객 이름: " + customer.getName());
		System.out.println("2. 전화번호: " + customer.getPhoneNumber());
		System.out.println("3. 직업: " + customer.getJob());
		System.out.println("4. 나이: " + customer.getAge());
		System.out.println("5. 성별: " + customer.getGender().getName());
		System.out.println("6. 주소: " + customer.getAddress());
		System.out.println("7. 재산: " + customer.getProperty());
		System.out.println("8. 사고 이력: " + customer.getAccidentHistoryList());
		System.out.println("9. 수술 이력: " + customer.getSurgeryHistoryList());
		System.out.println("10. 병력: " + customer.getDiseaseHistoryList());
		System.out.println("11. 은행명: " + customer.getBankName());
		System.out.println("12. 계좌 번호: " + customer.getBankAccount());

		try {
			int inputIndex = 0;
			do {
				input = scanner.next();
				inputIndex = Integer.parseInt(input);
				if (!(1 <= inputIndex && inputIndex <= 12)) {
					return;
				} else if (inputIndex == 5) {
					System.out.println("5. 성별");
					System.out.println("1. 남자 2. 여자");
				} else if (inputIndex == 8) {
//					updateAccidentHistory(customerInformationManagement);
				} else if (inputIndex == 9) {
//					updateSurgeryHistory(customerInformationManagement);
				} else if (inputIndex == 10) {
//					updateDiseaseHistory(customerInformationManagement);
				}
			} while (!(1 <= Integer.parseInt(input) && Integer.parseInt(input) <= 12));

			System.out.println(inputIndex + "번에 해당하는 새로운 값: ");
			String inputParameter = scanner.next();

			if (inputIndex == 5) {
				if (!(1 <= Integer.parseInt(inputParameter) && Integer.parseInt(inputParameter) <= 2)) {
					return;
				}
			}

			do {
				System.out.println("1. 수정 2. 취소");
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					do {
						System.out.println("정말로 수정하시겠습니까?");
						System.out.println("1. 확인 2. 취소");
						input = scanner.next();
						index = Integer.parseInt(input);
						switch (index) {
						case 1:
							customerInformationManagementModel.updateCustomerInformation(inputIndex, inputParameter,
									customer, customerList);
							System.out.println("수정되었습니다.");
							break;
						case 2:
							return;
						default:
							System.out.println("명시된 번호중에 클릭해주세요.");
						}
					} while (!(1 <= index && index <= 2));
					return;
				case 2:
					return;
				default:
					System.out.println("명시된 번호중에 클릭해주세요.");
				}
			} while (!(1 <= index && index <= 2));

		} catch (DuplicateResidentRegistrationNumberException | NotExistException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException numberFormatException) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
		}
	}

//	시나리오에 없는 내용
////	2024-06-04 김대현
//	private void updateAccidentHistory(CustomerInformationManagement customerInformationManagement) {
//	}
////	2024-06-04 김대현
//	private void updateSurgeryHistory(CustomerInformationManagement customerInformationManagement) {
//	}
////	2024-06-04 김대현
//	private void updateDiseaseHistory(CustomerInformationManagement customerInformationManagement) {
//	}

	// 2024-06-04 김대현
	private void deleteCustomerInformation(Employee employee, int id) {
		// CustomerInformationManagementModel employee
		do {
			System.out.println("정말로 삭제하시겠습니까?");
			System.out.println("1. 확인 2. 취소");
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				try {
					customerInformationManagementModel.deleteCustomerInformation(customerList, id);
				} catch (NotExistException e) {
					System.out.println(e.getMessage());
					return;
				}
				System.out.println("삭제되었습니다.");
				return;
			case 2:
				return;
			default:
				System.out.println("명시된 번호중에 클릭해주세요.");
			}
		} while (!(1 <= index && index <= 2));
	}
	// TODO Sales
	private void showSalesTask(Sales sales) {
		boolean finish = false;
		while (!finish) {
			System.out.println(sales.getName() + "님! 처리하려는 일을 클릭해주세요.");
			System.out.println("1. 영업 성과 평가 2. 보험 상담 처리 3. 보험 상품 영업 4. 대출 상품 영업");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);

				switch (index) {
				case 1:
					evaluateSalesPerformance(sales); // 영업 성과 평가
					break;
				case 2:
					handleInsuranceConsultation(sales);// 보험 상담 처리
					break;
				case 3:
					induceInsuranceProduct(sales);
					break;
				case 4:
					induceLoanProduct(sales);
					break;
				default:
					finish = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			}
		}
		index = -1;
	}

	private void evaluateSalesPerformance(Employee employee) {
//		영업 직원 정보 리스트(직원 정보(직원 번호, 직원 이름, 직급), 계약건수)
		for (Employee rEmployee : employeeList.getAll()) {
			// 여기
			if (rEmployee instanceof Sales) {
				System.out.print("직원 번호: " + ((Sales) rEmployee).getId() + " ");
				System.out.print("직원 이름: " + ((Sales) rEmployee).getName() + " ");
				System.out.print("직급: " + ((Sales) rEmployee).getPosition() + " ");
				System.out.println("계약건수: " + ((Sales) rEmployee).getContractCount());
				// 계약 건수는 Sales 타입이라서 Sales는 남겨야 함
			}
		}

		System.out.println("1. 검색 2. 더블클릭");
		try {
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				getSales(employee);
				break;
			case 2:
				doubleClickSalesPerformance(employee);
				break;
			}
		} catch (Exception e) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
		}
	}

	private void getSales(Employee employee) {
		System.out.print("검색창: ");
		input = scanner.next();
		id = Integer.parseInt(input);

		try {
			Employee rEmployee = employeeList.get(id);
			// 여기
			if (rEmployee instanceof Sales) {
				System.out.print("직원 번호: " + ((Sales) employeeList.get(id)) + " ");
				System.out.print("직원 이름: " + ((Sales) employeeList.get(id)) + " ");
				System.out.print("직급: " + ((Sales) employeeList.get(id)) + " ");
				System.out.println("계약건수: " + ((Sales) employeeList.get(id)).getContractCount());
			} else {
				System.out.println("해당하는 직원 정보가 존재하지 않습니다.");
			}
		} catch (NotExistException e) {
			System.out.println("해당하는 직원 " + e.getMessage());
		}
	}

	private void doubleClickSalesPerformance(Employee employee) {
		System.out.println("영업 사원의 번호(더블클릭): ");
		input = scanner.next();
		id = Integer.parseInt(input);
//		영업 직원 상세 정보(직원 상세 정보 (직원 번호, 직원 이름, 직급, 급여), )
		Sales sales;
		try {
			sales = employeeList.getSales(id);
			// 여기
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.print(sales.getId() + " ");
		System.out.print(sales.getName() + " ");
		System.out.print(sales.getPosition() + " ");
		System.out.print(sales.getSalary() + " ");
		System.out.println(sales.getContractCount());

		do {
			System.out.println("1. 평가 2. 취소");
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				int evaluate;
				System.out.println("1. ★ 2. ★★ 3. ★★★ 4. ★★★★ 5. ★★★★★");
				input = scanner.next();
				evaluate = Integer.parseInt(input);
				switch (evaluate) {
				case 1:
					evaluate = 1;
					break;
				case 2:
					evaluate = 2;
					break;
				case 3:
					evaluate = 3;
					break;
				case 4:
					evaluate = 4;
					break;
				case 5:
					evaluate = 5;
					break;
				default:
					return;
				}
				try {
					salesModel.evaluateSalesPerformance(evaluate, sales, employeeList);
					System.out.println("평가되었습니다.");
				} catch (NotExistException e) {
					System.out.println(e.getMessage());
					return;
				}
			case 2:
				return;
			default:
				System.out.println("명시된 번호중에 클릭해주세요.");
			}
		} while (!(1 <= index && index <= 2));
	}

	private void handleInsuranceConsultation(Employee employee) {
//		보험 상담 처리 정보 리스트(상담 고객 정보(고객 이름, 전화번호, 날짜, 성별), 상담 번호, 처리 상태)

		for (Counsel counsel : counselList.getAll()) {
			// 여기
			System.out.print("상담 번호: " + counsel.getId() + " ");
			System.out.print("고객 이름: " + counsel.getName() + " ");
			System.out.print("전화번호: " + counsel.getPhoneNumber() + " ");
			System.out.print("날짜: " + counsel.getCounselDate() + " ");
			System.out.print("성별: " + counsel.getGender().getName() + " ");
			System.out.println("처리 상태: " + counsel.getProcessStatus().getName());
		}

		try {
			System.out.println("1.처리상태 콤보박스 2.검색 3. 더블 클릭");
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				viewCombobox(employee);
				break;
			case 2:
				getCounsel(employee);
				break;
			case 3:
				doubleClickCounsel(employee);
				break;
			default:
				return;
			}
		} catch (Exception e) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			return;
		}
	}

	private void viewCombobox(Employee employee) {
		System.out.println("1.미처리 2.처리완료");
		input = scanner.next();
		index = Integer.parseInt(input);
		for (Counsel counsel : counselList.getAll()) {
			// 여기
			if (index == 1 && counsel.getProcessStatus() == CounselProcessStatus.Unprocessed) {
				System.out.print("상담 번호: " + counsel.getId() + " ");
				System.out.print("고객 이름: " + counsel.getName() + " ");
				System.out.print("전화번호: " + counsel.getPhoneNumber() + " ");
				System.out.print("날짜: " + counsel.getCounselDate() + " ");
				System.out.print("성별: " + counsel.getGender().getName() + " ");
				System.out.println("처리 상태: " + counsel.getProcessStatus().getName());
			} else if (index == 2 && counsel.getProcessStatus() == CounselProcessStatus.Unprocessed) {
				System.out.print("상담 번호: " + counsel.getId() + " ");
				System.out.print("고객 이름: " + counsel.getName() + " ");
				System.out.print("전화번호: " + counsel.getPhoneNumber() + " ");
				System.out.print("날짜: " + counsel.getCounselDate() + " ");
				System.out.print("성별: " + counsel.getGender().getName() + " ");
				System.out.println("처리 상태: " + counsel.getProcessStatus().getName());
			} else {
				return;
			}
		}
	}

	private void getCounsel(Employee employee) {
		System.out.print("검색창: ");
		input = scanner.next();
		id = Integer.parseInt(input);
		Counsel counsel;
		try {
			counsel = counselList.get(id);
			// 여기
		} catch (NotExistException e) {
			System.out.println("상담 " + e.getMessage());
			return;
		}
		System.out.print("상담 번호: " + counsel.getId() + " ");
		System.out.print("고객 이름: " + counsel.getName() + " ");
		System.out.print("전화번호: " + counsel.getPhoneNumber() + " ");
		System.out.print("날짜: " + counsel.getCounselDate() + " ");
		System.out.print("성별: " + counsel.getGender().getName() + " ");
		System.out.println("처리 상태: " + counsel.getProcessStatus().getName());
	}

//	보험 상담 처리 상세 정보(상담 고객 정보(고객 이름, 전화번호, 날짜, 직업,나이, 성별), 상담 번호, 처리 상태)
	private void doubleClickCounsel(Employee employee) {
		System.out.println("상담의 번호(더블클릭): ");
		input = scanner.next();
		id = Integer.parseInt(input);
		Counsel counsel;
		try {
			counsel = counselList.get(id);
			// 여기
		} catch (NotExistException e) {
			System.out.println("상담 " + e.getMessage());
			return;
		}
		System.out.print("상담 번호: " + counsel.getId() + " ");
		System.out.print("고객 이름: " + counsel.getName() + " ");
		System.out.print("전화번호: " + counsel.getPhoneNumber() + " ");
		System.out.print("날짜: " + counsel.getCounselDate() + " ");
		System.out.print("직업: " + counsel.getJob() + " ");
		System.out.print("나이: " + counsel.getAge() + " ");
		System.out.print("성별: " + counsel.getGender().getName() + " ");
		System.out.println("처리 상태: " + counsel.getProcessStatus().getName());

		System.out.println("1. 예약 2. 취소");
		input = scanner.next();
		index = Integer.parseInt(input);
		boolean finish = false;
		while (!finish) {
			switch (index) {
			case 1:
				try {
					salesModel.handleInsuranceConsultation(counsel, counselList);
					System.out.println("예약되었습니다");
				} catch (NotExistException e) {
					System.out.println(e.getMessage());
				} catch (AlreadyProcessedException e) {
					System.out.println("이미 상담 처리가 완료되었습니다.");
				}
				break;
			case 2:
				finish = true;
				break;
			default:
				System.out.print("명시된 번호 중에 클릭해주세요.");
				break;
			}
		}

	}

	private void induceInsuranceProduct(Sales sales) {
		System.out.println("보험 상품 정보 리스트");
		System.out.println("\n=================");
		for (Product e : productList.getAll()) {
			// 여기
			if (e instanceof Insurance) {
				System.out.print("보험 상품 번호: " + ((Insurance) e).getId() + "\n");
				System.out.print("보험 상품 이름: " + ((Insurance) e).getName() + " | ");
				System.out.print("보험 종류: " + ((Insurance) e).getInsuranceType().getName() + " | ");
				System.out.print("연령대: " + ((Insurance) e).getAgeRange() + " | ");
				System.out.println("월 보험료: " + ((Insurance) e).getMonthlyPremium());
				System.out.println("-----------------");
			}
		}
		System.out.println("=================\n");

		System.out.println("1. 보험 종류 콤보박스 2. 검색 3. 더블 클릭");
		try {
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				System.out.println("1. 보험 종류 콤보박스");
				System.out.println("1. 질병 2. 상해 3. 자동차");
				try {
					input = scanner.next();
					index = Integer.parseInt(input);
					switch (index) {
					case 1:
						induceDiseaseInsurance(sales);
						break;
					case 2:
						induceInjuryInsurance(sales);
						break;
					case 3:
						induceAutomobileInsurance(sales);
						break;
					default:
						return;
					}
				} catch (Exception e) {
					System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
				}
				break;
			case 2:
				getInsuranceSales(sales);
				break;
			case 3:
				doubleClickInsuranceSales(sales);
				break;
			default:
				return;
			}
		} catch (Exception e) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			return;
		}
	}

	private void getInsuranceSales(Employee employee) {
		System.out.print("검색창: ");
		input = scanner.next();
		id = Integer.parseInt(input);
		Insurance insurance;
		try {
			insurance = salesModel.getInsuranceProduct(productList, id);
		} catch (NotExistException e) {
			System.out.println("보험 상품 " + e.getMessage());
			return;
		}
		System.out.print("보험 상품 번호: " + insurance.getId() + "\n");
		System.out.print("보험 상품 이름: " + insurance.getName() + " | ");
		System.out.print("보험 종류: " + insurance.getInsuranceType().getName() + " | ");
		System.out.print("연령대: " + insurance.getAgeRange() + " | ");
		System.out.println(" 월 보험료: " + insurance.getMonthlyPremium());
	}

	private void doubleClickInsuranceSales(Sales sales) throws ParseException {
		// Sales employee
		System.out.println("보험 상품의 번호(더블클릭): ");
		input = scanner.next();
		id = Integer.parseInt(input);
		Insurance insurance;
		try {
			insurance = salesModel.getInsuranceProduct(productList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("1. 보험 상품 이름: " + insurance.getName());
		System.out.println("2. 보험 종류: " + insurance.getInsuranceType().getName());
		System.out.println("3. 연령대: " + insurance.getAgeRange());
		System.out.println("4. 보장 내용: " + insurance.getCoverage());
		System.out.println("5. 월 보험료: " + insurance.getMonthlyPremium());
		System.out.println("6. 계약기간: " + insurance.getContractPeriod());
		if (insurance instanceof Disease) {
			Disease diseaseInsurance = (Disease) insurance;
			System.out.println("7. 질병 최대 개수: " + diseaseInsurance.getDiseaseLimit());
			System.out.println("8. 질병 이름: " + diseaseInsurance.getDiseaseName());
			System.out.println("9. 수술 최대 횟수: " + diseaseInsurance.getSurgeriesLimit());
		} else if (insurance instanceof Injury) {
			Injury injuryInsurance = (Injury) insurance;
			System.out.println("7. 상해 보험 종류: " + injuryInsurance.getInjuryType().getName());
			System.out.println("8. 수술 최대 횟수: " + injuryInsurance.getSurgeriesLimit());
		} else if (insurance instanceof Automobile) {
			Automobile automobileInsurance = (Automobile) insurance;
			System.out.println("7. 사고 최대 횟수: " + automobileInsurance.getAccidentLimit());
			System.out.println("8. 차량 종류: " + automobileInsurance.getVehicleType().getName());
			System.out.print("9. 서비스 종류:");
			for (ServiceType serviceType : automobileInsurance.getServiceList()) {
				System.out.print(" " + serviceType.getName() + " |");
			}
			System.out.println();
		}
		System.out.println("1. 안내장 발송 2. 보험 가입 요청");
		input = scanner.next();
		index = Integer.parseInt(input);
		switch (index) {
		case 1:
			System.out.println("안내장 발송이 완료되었습니다.");
			break;
		case 2:
			Customer customer = new Customer();
			boolean finish = false;
			while (!finish) {
				try {
					System.out.print("1. 고객 이름: ");
					input = scanner.next();
					String name = input;
					System.out.print("2. 전화번호: ");
					input = scanner.next();
					String phoneNumber = input;
					System.out.print("3. 직업: ");
					input = scanner.next();
					String job = input;
					System.out.print("4. 나이: ");
					input = scanner.next();
					int age = Integer.parseInt(input);

					Gender gender = null;
					System.out.println("5. 성별: ");
					System.out.println("1. 남자 2. 여자");
					input = scanner.next();
					switch (Integer.parseInt(input)) {
					case 1:
						gender = Gender.Male;
						break;
					case 2:
						gender = Gender.Female;
						break;
					default:
						return;
					}

					System.out.print("6. 주민등록번호: ");
					input = scanner.next();
					String residentRegistrationNumber = input;
					System.out.print("7. 주소: ");
					input = scanner.next();
					String address = input;
					System.out.print("8. 재산: ");
					input = scanner.next();
					long property = Integer.parseInt(input);

					ArrayList<AccidentHistory> tempAccidentHistoryList = new ArrayList<AccidentHistory>();
					boolean finishInputAccidentHistory = false;
					while (!finishInputAccidentHistory) {
						System.out.println("9. 사고 이력: ");
						System.out.println("1. 입력 2. 취소");
						input = scanner.next();
						switch (Integer.parseInt(input)) {
						case 1:
							System.out.print("사고 내용:");
							String detailsOfAccident = scanner.next();
							System.out.print("사고날짜(yyyy-MM-dd) :");
							String dateOfAccident = scanner.next();
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
							Date date = formatter.parse(dateOfAccident);
							AccidentHistory accidentHistory = new AccidentHistory(detailsOfAccident, date);
							tempAccidentHistoryList.add(accidentHistory);
							break;
						case 2:
							finishInputAccidentHistory = true;
							break;
						default:
							System.out.println("명시된 번호 중에 클릭해주세요.");
							break;
						}
					}

					ArrayList<SurgeryHistory> tempSurgeryHistoryList = new ArrayList<SurgeryHistory>();
					boolean finishSurgeryHistory = false;
					while (!finishSurgeryHistory) {
						System.out.println("10. 수술 이력");
						System.out.println("1. 입력 2. 취소");
						input = scanner.next();
						switch (Integer.parseInt(input)) {
						case 1:
							System.out.print("수술명:");
							String surgeryName = scanner.next();
							System.out.print("병원이름:");
							String hospitalName = scanner.next();
							System.out.print("수술날짜(yyyy-MM-dd) :");
							String dateOfSurgery = scanner.next();
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
							Date date = formatter.parse(dateOfSurgery);
							SurgeryHistory surgeryHistory = new SurgeryHistory(surgeryName, hospitalName, date);
							tempSurgeryHistoryList.add(surgeryHistory);
							break;
						case 2:
							finishSurgeryHistory = true;
							break;
						default:
							System.out.println("명시된 번호 중에 클릭해주세요.");
							break;
						}
					}

					ArrayList<DiseaseHistory> tempDiseaseHistoryList = new ArrayList<DiseaseHistory>();
					boolean finishInputDiseaseHistory = false;
					while (!finishInputDiseaseHistory) {
						System.out.println("11. 병력");
						System.out.println("1. 입력 2. 취소");
						input = scanner.next();
						switch (Integer.parseInt(input)) {
						case 1:
							System.out.print("병명:");
							String diseaseName = scanner.next();
							System.out.print("진단일(yyyy-MM-dd) :");
							String dateOfDiagnosis = scanner.next();
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
							Date date = formatter.parse(dateOfDiagnosis);
							DiseaseHistory diseaseHistory = new DiseaseHistory(diseaseName, date);
							diseaseHistoryList.add(diseaseHistory);
							break;
						case 2:
							finishInputDiseaseHistory = true;
							break;
						default:
							System.out.println("명시된 번호 중에 클릭해주세요.");
							break;
						}
					}

					System.out.print("12. 은행명: ");
					input = scanner.next();
					String bankName = input;
					System.out.print("13. 계좌 번호: ");
					input = scanner.next();
					String bankAccount = input;

					do {
						System.out.println("1. 확인 2. 취소");
						input = scanner.next();
						index = Integer.parseInt(input);
						switch (index) {
						case 1:
							customer.signUp(name, phoneNumber, job, age, gender, residentRegistrationNumber, address,
									property, tempAccidentHistoryList, tempSurgeryHistoryList, tempDiseaseHistoryList,
									bankName, bankAccount, customerList, accidentHistoryList, surgeryHistoryList,
									diseaseHistoryList);
							System.out.println("회원가입이 완료되었습니다.회원 아이디는 " + customer.getId() + "입니다.");
							finish = true;
							break;
						case 2:
							return;
						default:
							System.out.println("명시된 번호중에 클릭해주세요.");
						}
					} while (!(1 <= index && index <= 2));
				} catch (DuplicateResidentRegistrationNumberException duplicateresidentRegistrationNumber) {
					System.out.println(duplicateresidentRegistrationNumber.getMessage());
					return;
				} catch (NumberFormatException numberFormatException) {
					System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
					return;
				}
				while (true) {
					System.out.println("1. 보험 가입 요청 2. 취소");
					int nextStep = scanner.nextInt();
					if (nextStep == 1) {
						customer.buyInsurance(insurance, contractList);
						int contractCount = sales.getContractCount();
						sales.setContractCount(++contractCount);
						try {
							employeeList.update(sales);
							System.out.println("요청되었습니다.");
						} catch (NotExistException e) {
							System.out.println(e.getMessage());
							return;
						}
						break;
					} else if (nextStep == 2) {
						break;
					} else {
						System.out.println("명시된 번호중에 클릭해주세요.");
					}
				}
			}
			break;
		default:
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
		}
	}

	private void induceDiseaseInsurance(Employee employee) {
		ArrayList<Insurance> insuranceList = productList.getAllDiseaseInsurance();
		// 여기
		System.out.println("질병 보험 상품 정보 리스트");
		System.out.println("\n=================");
		for (Insurance insurance : insuranceList) {
			System.out.print("보험 상품 번호: " + insurance.getId() + "\n");
			System.out.print("보험 상품 이름: " + insurance.getName() + " | ");
			System.out.print("보험 종류: " + insurance.getInsuranceType().getName() + " | ");
			System.out.print("연령대: " + insurance.getAgeRange() + " | ");
			System.out.println("월 보험료: " + insurance.getMonthlyPremium());
			System.out.println("-----------------");
		}
		System.out.println("=================\n");
	}

	private void induceInjuryInsurance(Employee employee) {
		ArrayList<Insurance> insuranceList = productList.getAllInjuryInsurance();
		// 여기
		System.out.println("상해 보험 상품 정보 리스트");
		System.out.println("\n=================");
		for (Insurance insurance : insuranceList) {
			System.out.print("보험 상품 번호: " + insurance.getId() + "\n");
			System.out.print("보험 상품 이름: " + insurance.getName() + " | ");
			System.out.print("보험 종류: " + insurance.getInsuranceType().getName() + " | ");
			System.out.print("연령대: " + insurance.getAgeRange() + " | ");
			System.out.println("월 보험료: " + insurance.getMonthlyPremium());
			System.out.println("-----------------");
		}
		System.out.println("=================\n");
	}

	private void induceAutomobileInsurance(Employee employee) {
		ArrayList<Insurance> insuranceList = productList.getAllAutomobileInsurance();
		// 여기
		System.out.println("자동차 보험 상품 정보 리스트");
		System.out.println("\n=================");
		for (Insurance insurance : insuranceList) {
			System.out.print("보험 상품 번호: " + insurance.getId() + "\n");
			System.out.print("보험 상품 이름: " + insurance.getName() + " | ");
			System.out.print("보험 종류: " + insurance.getInsuranceType().getName() + " | ");
			System.out.print("연령대: " + insurance.getAgeRange() + " | ");
			System.out.println("월 보험료: " + insurance.getMonthlyPremium());
			System.out.println("-----------------");
		}
		System.out.println("=================\n");
	}

	/////////// 2024-06-04 모지환
	private void induceLoanProduct(Employee employee) {
		System.out.println("대출 상품 정보 리스트");
		System.out.println("\n=================");
		for (Product e : productList.getAll()) {
			// 여기
			if (e instanceof Loan) {
				System.out.print("대출 상품 번호: " + ((Loan) e).getId() + "\n");
				System.out.print("대출 상품 이름: " + ((Loan) e).getName() + " | ");
				System.out.print("대출 종류: " + ((Loan) e).getLoanType().getName() + " | ");
				System.out.print("이자율: " + ((Loan) e).getInterestRate() + " | ");
				System.out.println("대출 가능 최대 금액: " + ((Loan) e).getLimit() + " | ");
				System.out.println("-----------------");
			}
		}
		System.out.println("=================\n");

		System.out.println("1. 대출 종류 콤보박스 2. 검색 3. 더블 클릭");
		try {
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				System.out.println("1. 대출 종류 콤보박스");
				System.out.println("1. 담보 2. 정기 예금 3. 보험 계약");
				try {
					input = scanner.next();
					index = Integer.parseInt(input);
					switch (index) {
					case 1:
						induceCollateralLoan(employee);
						break;
					case 2:
						induceFixedDepositLoan(employee);
						break;
					case 3:
						induceInsuranceContractLoan(employee);
						break;
					default:
						return;
					}
				} catch (Exception e) {
					System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
				}
				break;
			case 2:
				getLoanSales(employee);
				break;
			case 3:
				doubleClickLoanSales(employee);
				break;
			default:
				return;
			}
		} catch (Exception e) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
		}
	}

	private void getLoanSales(Employee employee) {
		System.out.print("검색창: ");
		input = scanner.next();
		id = Integer.parseInt(input);
		Loan loan;
		try {
			loan = salesModel.getLoanProduct(productList, id);
		} catch (NotExistException e) {
			System.out.println("대출 상품 " + e.getMessage());
			return;
		}
		
		System.out.print("대출 상품 이름: " + loan.getName() + " ");
		System.out.print("대출 종류: " + loan.getLoanType().getName() + " ");
		System.out.print("대출 상품 번호: " + loan.getId() + " ");
		System.out.print("이자율: " + loan.getInterestRate() + " ");
		System.out.println("대출 가능 최대 금액: " + loan.getLimit() + " ");	
	}

	private void doubleClickLoanSales(Employee employee) throws ParseException {
		System.out.println("대출 상품의 번호(더블클릭): ");
		input = scanner.next();
		id = Integer.parseInt(input);

		try {
			if (salesModel.getLoanProduct(productList, id) != null) {
				System.out.println("1. 대출 상품 이름: " + salesModel.getLoanProduct(productList, id).getName() + " ");
				System.out.println("2. 대출 종류: " + salesModel.getLoanProduct(productList, id).getLoanType().getName() + " ");
				System.out.println("3. 대출 상품 번호: " + salesModel.getLoanProduct(productList, id).getId() + " ");
				System.out.println("4. 이자율: " + salesModel.getLoanProduct(productList, id).getInterestRate() + " ");
				System.out.println("5. 대출 가능 최대 금액: " + salesModel.getLoanProduct(productList, id).getLimit() + " ");
				System.out.println("6. 최소 자산: " + salesModel.getLoanProduct(productList, id).getMinimumAsset());
				if (salesModel.getLoanProduct(productList, id) instanceof Collateral) {
					Collateral collateralLoan = (Collateral) salesModel.getLoanProduct(productList, id);
					System.out.println("7. 담보 종류: " + collateralLoan.getCollateralType().getName());
					System.out.println("8. 담보 최소 가치: " + collateralLoan.getMinimumValue());
				} else if (salesModel.getLoanProduct(productList, id) instanceof FixedDeposit) {
					FixedDeposit fixedDepositLoan = (FixedDeposit) salesModel.getLoanProduct(productList, id);
					System.out.println("7. 최대 예치 금액: " + fixedDepositLoan.getMinimumAmount());
				} else if (salesModel.getLoanProduct(productList, id) instanceof InsuranceContract) {
					InsuranceContract insuranceContractLoan = (InsuranceContract) salesModel.getLoanProduct(productList, id);
					System.out.println("7. 보험 상품 번호: " + insuranceContractLoan.getProductID());
				}
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("대출 상품 정보가 존재하지 않습니다.");
			return;
		}
		System.out.println("1. 안내장 발송 2. 대출 요청");
		input = scanner.next();
		index = Integer.parseInt(input);
		switch (index) {
		case 1:
			System.out.println("안내장 발송이 완료되었습니다.");
			break;
		case 2:
			Customer customer = new Customer();
			boolean finish = false;
			while (!finish) {
				try {
					System.out.print("1. 고객 이름: ");
					input = scanner.next();
					String name = input;
					System.out.print("2. 전화번호: ");
					input = scanner.next();
					String phoneNumber = input;
					System.out.print("3. 직업: ");
					input = scanner.next();
					String job = input;
					System.out.print("4. 나이: ");
					input = scanner.next();
					int age = Integer.parseInt(input);

					Gender gender = null;
					System.out.println("5. 성별: ");
					System.out.println("1. 남자 2. 여자");
					input = scanner.next();
					switch (Integer.parseInt(input)) {
					case 1:
						gender = Gender.Male;
						break;
					case 2:
						gender = Gender.Female;
						break;
					default:
						return;
					}

					System.out.print("6. 주민등록번호: ");
					input = scanner.next();
					String residentRegistrationNumber = input;
					System.out.print("7. 주소: ");
					input = scanner.next();
					String address = input;
					System.out.print("8. 재산: ");
					input = scanner.next();
					long property = Integer.parseInt(input);

					ArrayList<AccidentHistory> tempAccidentHistoryList = new ArrayList<AccidentHistory>();
					System.out.println("9. 사고 이력: ");
					System.out.println("1. 입력 2. 취소");
					boolean finishInputAccidentHistory = false;
					while (!finishInputAccidentHistory) {
						input = scanner.next();
						switch (Integer.parseInt(input)) {
						case 1:
							System.out.print("사고 내용:");
							String detailsOfAccident = scanner.next();
							System.out.print("사고날짜(yyyy-MM-dd) :");
							String dateOfAccident = scanner.next();
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
							Date date = formatter.parse(dateOfAccident);
							AccidentHistory accidentHistory = new AccidentHistory(detailsOfAccident, date);
							tempAccidentHistoryList.add(accidentHistory);
							break;
						case 2:
							finishInputAccidentHistory = true;
							break;
						default:
							System.out.println("명시된 번호 중에 클릭해주세요.");
							break;
						}
					}

					ArrayList<SurgeryHistory> tempSurgeryHistoryList = new ArrayList<SurgeryHistory>();
					System.out.println("10. 수술 이력");
					System.out.println("1. 입력 2. 취소");
					boolean finishSurgeryHistory = false;
					while (!finishSurgeryHistory) {
						input = scanner.next();
						switch (Integer.parseInt(input)) {
						case 1:
							System.out.print("수술명:");
							String surgeryName = scanner.next();
							System.out.print("병원이름:");
							String hospitalName = scanner.next();
							System.out.print("수술날짜(yyyy-MM-dd) :");
							String dateOfSurgery = scanner.next();
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
							Date date = formatter.parse(dateOfSurgery);
							SurgeryHistory surgeryHistory = new SurgeryHistory(surgeryName, hospitalName, date);
							tempSurgeryHistoryList.add(surgeryHistory);
							break;
						case 2:
							finishSurgeryHistory = true;
							break;
						default:
							System.out.println("명시된 번호 중에 클릭해주세요.");
							break;
						}
					}

					ArrayList<DiseaseHistory> tempDiseaseHistoryList = new ArrayList<DiseaseHistory>();
					System.out.println("11. 병력");
					System.out.println("1. 입력 2. 취소");
					boolean finishInputDiseaseHistory = false;
					while (!finishInputDiseaseHistory) {

						input = scanner.next();
						switch (Integer.parseInt(input)) {
						case 1:
							System.out.print("병명:");
							String diseaseName = scanner.next();
							System.out.print("진단일(yyyy-MM-dd) :");
							String dateOfDiagnosis = scanner.next();
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
							Date date = formatter.parse(dateOfDiagnosis);
							DiseaseHistory diseaseHistory = new DiseaseHistory(diseaseName, date);
							diseaseHistoryList.add(diseaseHistory);
							break;
						case 2:
							finishInputDiseaseHistory = true;
							break;
						default:
							System.out.println("명시된 번호 중에 클릭해주세요.");
							break;
						}
					}

					System.out.print("12. 은행명: ");
					input = scanner.next();
					String bankName = input;
					System.out.print("13. 계좌 번호: ");
					input = scanner.next();
					String bankAccount = input;

					do {
						System.out.println("1. 확인 2. 취소");
						input = scanner.next();
						index = Integer.parseInt(input);
						switch (index) {
						case 1:
							customer.signUp(name, phoneNumber, job, age, gender, residentRegistrationNumber, address,
									property, tempAccidentHistoryList, tempSurgeryHistoryList, tempDiseaseHistoryList,
									bankName, bankAccount, customerList, accidentHistoryList, surgeryHistoryList,
									diseaseHistoryList);
							System.out.println("회원가입이 완료되었습니다. 회원 아이디는 " + customer.getId() + "입니다.");
							finish = true;
							break;
						case 2:
							return;
						default:
							System.out.println("명시된 번호중에 클릭해주세요.");
						}
					} while (!(1 <= index && index <= 2));
				} catch (DuplicateResidentRegistrationNumberException duplicateresidentRegistrationNumber) {
					System.out.println(duplicateresidentRegistrationNumber.getMessage());
					return;
				} catch (NumberFormatException numberFormatException) {
					System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
					return;
				}
				while (true) {
					System.out.println("1. 대출 요청 2. 취소");
					int nextStep = scanner.nextInt();
					if (nextStep == 1) {
						System.out.println("요청되었습니다.");
						break;
					} else if (nextStep == 2) {
						break;
					} else {
						System.out.println("명시된 번호중에 클릭해주세요.");
					}
				}
				break;
			}
		default:
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
		}
	}

	private void induceCollateralLoan(Employee employee) {
		ArrayList<Loan> loanList = productList.getAllCollateralLoan();
		// 여기
		System.out.println("담보 대출 상품 정보 리스트");
		for (Loan loan : loanList) {
			System.out.print("대출 상품 이름: " + loan.getName() + " ");
			System.out.print("대출 종류: " + loan.getLoanType().getName() + " ");
			System.out.print("대출 상품 번호: " + loan.getId() + " ");
			System.out.print("이자율: " + loan.getInterestRate() + " ");
			System.out.println("대출 가능 최대 금액: " + loan.getLimit());
			// System.out.println("대출 상태: " + ((Loan) e).get() + " ");
		}
	}

	private void induceFixedDepositLoan(Employee employee) {
		ArrayList<Loan> loanList = productList.getAllFixedDepositLoan();
		System.out.println("정기 예금 대출 상품 정보 리스트");
		for (Loan loan : loanList) {
			System.out.print("대출 상품 이름: " + loan.getName() + " ");
			System.out.print("대출 종류: " + loan.getLoanType().getName() + " ");
			System.out.print("대출 상품 번호: " + loan.getId() + " ");
			System.out.print("이자율: " + loan.getInterestRate() + " ");
			System.out.println("대출 가능 최대 금액: " + loan.getLimit());
			// System.out.println("대출 상태: " + ((Loan) e).get() + " ");
		}
	}

	private void induceInsuranceContractLoan(Employee employee) {
		ArrayList<Loan> loanList = productList.getAllInsuranceContractLoan();
		System.out.println("보험 계약 대출 상품 정보 리스트");
		for (Loan loan : loanList) {
			System.out.print("대출 상품 이름: " + loan.getName() + " ");
			System.out.print("대출 종류: " + loan.getLoanType().getName() + " ");
			System.out.print("대출 상품 번호: " + loan.getId() + " ");
			System.out.print("이자율: " + loan.getInterestRate() + " ");
			System.out.println("대출 가능 최대 금액: " + loan.getLimit());
			// System.out.println("대출 상태: " + ((Loan) e).get() + " ");
		}
	}

///////////////////////////////////////////////////////////////////

	// 2024-05-29 김대현
	// 2024-05-31 김대현
	// TODO ProductManagement
	private void showProductManagementTask(Employee employee) {
		// 600090
		index = -1;
		boolean finish = false;
		while (!finish) {
			System.out.println(employee.getName() + "님! 처리하려는 일을 클릭해주세요.");
			System.out.println("1. 보험 상품 관리");
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				manageInsuranceProduct(employee);
				break;
			default:
				finish = true;
				break;
			}
		}
		index = -1;
	}

	// 2024-05-31 김대현
	private void manageInsuranceProduct(Employee employee) {

		System.out.println("보험 상품 정보 리스트");
		System.out.println("\n=================");
		for (Product e : productList.getAll()) {
			if (e instanceof Insurance) {
				System.out.print("보험 상품 번호: " + ((Insurance) e).getId() + "\n");
				System.out.print("보험 상품 이름: " + ((Insurance) e).getName() + " | ");
				System.out.print("보험 종류: " + ((Insurance) e).getInsuranceType().getName() + " | ");
				System.out.print("연령대: " + ((Insurance) e).getAgeRange() + " | ");
				System.out.println("월 보험료: " + ((Insurance) e).getMonthlyPremium());
				System.out.println("-----------------");
			}
		}
		System.out.println("=================\n");

		System.out.println("1. 등록 2. 검색 3. 더블클릭");
		try {
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				System.out.println("보험 종류 콤보박스");
				System.out.println("1. 질병 2. 상해 3. 자동차");
				try {
					input = scanner.next();
					index = Integer.parseInt(input);
					switch (index) {
					case 1:
						createDiseaseInsurance(employee);
						break;
					case 2:
						createInjuryInsurance(employee);
						break;
					case 3:
						createAutomobileInsurance(employee);
						break;
					default:
						return;
					}
				} catch (Exception e) {
					System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
				}
				break;
			case 2:
				getInsurance(employee);
				break;
			case 3:
				doubleClickInsurance(employee);
				break;
			default:
				return;
			}
		} catch (Exception e) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
		}
	}

	// 2024-05-31 김대현
//  2024-06-04 김대현
	private void createDiseaseInsurance(Employee employee) {
		// ProductManagementModel employee
		boolean finish = false;
		while (!finish) {
			try {
				System.out.println("1. 보험 종류 : 질병");
				InsuranceType insuranceType = InsuranceType.Disease;

				System.out.print("2. 보험 상품 이름: ");
				input = scanner.next();
				String name = input;

				System.out.print("3. 한도: ");
				input = scanner.next();
				int limit = Integer.parseInt(input);
				System.out.print("4. 연령대: ");
				input = scanner.next();
				int ageRange = Integer.parseInt(input);
				System.out.print("5. 보장 내용: ");
				input = scanner.next();
				String coverage = input;
				System.out.print("6. 월 보험료: ");
				input = scanner.next();
				int monthlyPremium = Integer.parseInt(input);
				System.out.print("7. 계약기간: ");
				input = scanner.next();
				int contractPeriod = Integer.parseInt(input);

				System.out.print("8. 질병 이름: ");
				input = scanner.next();
				String diseaseName = input;
				System.out.print("9. 최대 질병 수: ");
				input = scanner.next();
				int diseaseLimit = Integer.parseInt(input);
				System.out.print("10. 최대 수술 횟수: ");
				input = scanner.next();
				int surgeriesLimit = Integer.parseInt(input);

				do {
					System.out.println("1. 확인 2. 취소");
					input = scanner.next();
					index = Integer.parseInt(input);
					switch (index) {
					case 1:
						productManagementModel.addInsuranceProduct(insuranceType, name, limit, ageRange, coverage,
								monthlyPremium, contractPeriod, diseaseName, diseaseLimit, surgeriesLimit, productList);
						System.out.println("등록되었습니다.");
						finish = true;
						break;
					case 2:
						return;
					default:
						System.out.println("등록된 번호중에 클릭해주세요.");
					}
				} while (!(1 <= index && index <= 2));
			} catch (DuplicateInsuranceException duplicateProductExeption) {
				System.out.println(duplicateProductExeption.getMessage());
				return;
			} catch (NumberFormatException numberFormatException) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
				return;
			}
		}
	}

	// 2024-05-31 김대현
//  2024-06-04 김대현
	private void createInjuryInsurance(Employee employee) {
		// ProductManagementModel employee
		boolean finish = false;
		while (!finish) {
			try {
				System.out.println("1. 보험 종류 : 상해");
				InsuranceType insuranceType = InsuranceType.Injury;

				System.out.print("2. 보험 상품 이름: ");
				input = scanner.next();

				String name = input;
				System.out.print("3. 한도: ");
				input = scanner.next();
				int limit = Integer.parseInt(input);
				System.out.print("4. 연령대: ");
				input = scanner.next();
				int ageRange = Integer.parseInt(input);
				System.out.print("5. 보장 내용: ");
				input = scanner.next();
				String coverage = input;
				System.out.print("6. 월 보험료: ");
				input = scanner.next();
				int monthlyPremium = Integer.parseInt(input);
				System.out.print("7. 계약기간: ");
				input = scanner.next();
				int contractPeriod = Integer.parseInt(input);
//				int years = Integer.parseInt(input);
//				LocalDate currentDate = LocalDate.now();
//				LocalDate contractDate = currentDate.plusYears(years);
//				Date contractPeriod = Date.from(contractDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

				InjuryType injuryType = null;
				System.out.println("8. 상해 종류");
				System.out.println("1.경상 2. 중상");
				input = scanner.next();
				switch (Integer.parseInt(input)) {
				case 1:
					injuryType = InjuryType.Minor;
					break;
				case 2:
					injuryType = InjuryType.Serious;
					break;
				default:
					return;
				}

				System.out.print("9. 최대 수술횟수: ");
				input = scanner.next();
				int surgeriesLimit = Integer.parseInt(input);

				do {
					System.out.println("1. 확인 2. 취소");
					input = scanner.next();
					index = Integer.parseInt(input);
					switch (index) {
					case 1:
						productManagementModel.addInsuranceProduct(insuranceType, name, limit, ageRange, coverage,
								monthlyPremium, contractPeriod, injuryType, surgeriesLimit, productList);
						System.out.println("등록되었습니다.");
						finish = true;
						break;
					case 2:
						return;
					default:
						System.out.println("명시된 번호중에 클릭해주세요.");
					}
				} while (!(1 <= index && index <= 2));
			} catch (DuplicateInsuranceException duplicateProductExeption) {
				System.out.println(duplicateProductExeption.getMessage());
				return;
			} catch (NumberFormatException numberFormatException) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
				return;
			}
		}
	}

	// 2024-05-31 김대현
//  2024-06-04 김대현
	private void createAutomobileInsurance(Employee employee) {
		// ProductManagementModel employee
		boolean finish = false;
		while (!finish) {
			try {
				System.out.println("1. 보험 종류 : 자동차");
				InsuranceType insuranceType = InsuranceType.Automobile;

				System.out.print("2. 보험 상품 이름: ");
				input = scanner.next();
				String name = input;
				System.out.print("3. 한도: ");
				input = scanner.next();
				int limit = Integer.parseInt(input);
				System.out.print("4. 연령대: ");
				input = scanner.next();
				int ageRange = Integer.parseInt(input);
				System.out.print("5. 보장 내용: ");
				input = scanner.next();
				String coverage = input;
				System.out.print("6. 월 보험료: ");
				input = scanner.next();
				int monthlyPremium = Integer.parseInt(input);
				System.out.print("7. 계약기간: ");
				input = scanner.next();
				int contractPeriod = Integer.parseInt(input);
//				int years = Integer.parseInt(input);
//				LocalDate currentDate = LocalDate.now();
//				LocalDate contractDate = currentDate.plusYears(years);
//				Date contractPeriod = Date.from(contractDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

				System.out.print("8. 최대 사고 횟수: ");
				input = scanner.next();
				int accidentLimit = Integer.parseInt(input);
				VehicleType vehicleType = null;
				System.out.println("9. 차량 종류");
				System.out.println("1. 소형 2. 중형 3. 대형");
				input = scanner.next();
				switch (Integer.parseInt(input)) {
				case 1:
					vehicleType = VehicleType.Small;
					break;
				case 2:
					vehicleType = VehicleType.Medium;
					break;
				case 3:
					vehicleType = VehicleType.Large;
					break;
				default:
					return;
				}

				ServiceType serviceType = null;
				ArrayList<ServiceType> serviceTypeList = new ArrayList<ServiceType>();
				boolean finish2 = false;
				while (!finish2) {
					System.out.println("10. 서비스 종류");
					System.out.println("1.긴급견인 2. 긴급시동 3. 비상급유 4. 배터리충전 5. 엔진과열 수리 6. 타이어펑크 수리 ");
					input = scanner.next();
					switch (Integer.parseInt(input)) {
					case 1:
						serviceType = ServiceType.EmergencyTowing;
						serviceTypeList.add(serviceType);
						break;
					case 2:
						serviceType = ServiceType.EmergencyStart;
						serviceTypeList.add(serviceType);
						break;
					case 3:
						serviceType = ServiceType.EmergencyRefueling;
						serviceTypeList.add(serviceType);
						break;
					case 4:
						serviceType = ServiceType.BatteryCharging;
						serviceTypeList.add(serviceType);
						break;
					case 5:
						serviceType = ServiceType.EngineOverheatingRepair;
						serviceTypeList.add(serviceType);
						break;
					case 6:
						serviceType = ServiceType.TirepunkRepair;
						serviceTypeList.add(serviceType);
						break;
					default:
						return;
					}
					do {
						System.out.println("더 선택하시겠습니까?.");
						System.out.println("1. 예 2. 아니오");
						input = scanner.next();
						switch (Integer.parseInt(input)) {
						case 1:
							break;
						case 2:
							finish2 = true;
							break;
						default:
							System.out.println("명시된 번호 중에 클릭해주세요.");
							break;
						}
					} while (!(1 <= Integer.parseInt(input) && Integer.parseInt(input) <= 2));
				}

				do {
					System.out.println("1. 확인 2. 취소");
					input = scanner.next();
					index = Integer.parseInt(input);
					switch (index) {
					case 1:
						productManagementModel.addInsuranceProduct(insuranceType, name, limit, ageRange, coverage,
								monthlyPremium, contractPeriod, accidentLimit, vehicleType, serviceTypeList,
								productList);
						System.out.println("등록되었습니다.");
						finish = true;
						break;
					case 2:
						return;
					default:
						System.out.println("명시된 번호중에 클릭해주세요.");
					}
				} while (!(1 <= index && index <= 2));
			} catch (DuplicateInsuranceException duplicateProductExeption) {
				System.out.println(duplicateProductExeption.getMessage());
				return;
			} catch (NumberFormatException numberFormatException) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
				return;
			}
		}
	}

	// 2024-05-31 김대현
	private void getInsurance(Employee employee) {
		// ProductManagementModel employee
		System.out.print("검색창: ");
		input = scanner.next();
		id = Integer.parseInt(input);
		Insurance insurance;
		try {
			insurance = productManagementModel.getInsuranceProduct(productList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("\n=================");
		System.out.print("보험 상품 번호: " + insurance.getId() + "\n");
		System.out.print("보험 상품 이름: " + insurance.getName() + " | ");
		System.out.print("보험 종류: " + insurance.getInsuranceType().getName() + " | ");
		System.out.print("연령대: " + insurance.getAgeRange() + " | ");
		System.out.println(" 월 보험료: " + insurance.getMonthlyPremium());
		System.out.println("=================\n");
	}

	// 2024-05-31 김대현
	private void doubleClickInsurance(Employee employee) {
		// ProductManagementModel employee
		System.out.println("보험 상품의 번호(더블클릭): ");
		input = scanner.next();
		id = Integer.parseInt(input);
		
		Insurance insurance;
		try {
			insurance = productManagementModel.getInsuranceProduct(productList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("1. 보험 상품 이름: " + insurance.getName());
		System.out.println("2. 보험 종류: " + insurance.getInsuranceType().getName());
		System.out.println("3. 연령대: " + insurance.getAgeRange());
		System.out.println("4. 보장 내용: " + insurance.getCoverage());
		System.out.println("5. 월 보험료: " + insurance.getMonthlyPremium());
		System.out.println("6. 계약기간: " + insurance.getContractPeriod());
		if (insurance instanceof Disease) {
			Disease diseaseInsurance = (Disease) insurance;
			System.out.println("7. 질병 최대 개수: " + diseaseInsurance.getDiseaseLimit());
			System.out.println("8. 질병 이름: " + diseaseInsurance.getDiseaseName());
			System.out.println("9. 수술 최대 횟수: " + diseaseInsurance.getSurgeriesLimit());
		} else if (insurance instanceof Injury) {
			Injury injuryInsurance = (Injury) insurance;
			System.out.println("7. 상해 보험 종류: " + injuryInsurance.getInjuryType().getName());
			System.out.println("8. 수술 최대 횟수: " + injuryInsurance.getSurgeriesLimit());
		} else if (insurance instanceof Automobile) {
			Automobile automobileInsurance = (Automobile) insurance;
			System.out.println("7. 사고 최대 횟수: " + automobileInsurance.getAccidentLimit());
			System.out.println("8. 차량 종류: " + automobileInsurance.getVehicleType().getName());
			System.out.println("9. 서비스 종류: " + automobileInsurance.getServiceList());
		}

		System.out.println("1. 수정 2. 삭제");
		input = scanner.next();
		index = Integer.parseInt(input);
		switch (index) {
		case 1:
			updateInsurance(employee, id);
			break;
		case 2:
			deleteInsurance(employee, id);
			break;
		default:
			return;
		}
	}

	// 2024-05-31 김대현
	private void updateInsurance(Employee employee, int id) {
		// ProductManagementModel employee
		Insurance insurance;
		try {
			insurance = productManagementModel.getInsuranceProduct(productList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("보험 상품 번호: " + insurance.getId());
		System.out.println("보험 종류: " + insurance.getInsuranceType().getName());

		System.out.println("1. 보험 상품 이름: " + insurance.getName());
		System.out.println("2. 연령대: " + insurance.getAgeRange());
		System.out.println("3. 보장 내용: " + insurance.getCoverage());
		System.out.println("4. 월 보험료: " + insurance.getMonthlyPremium());
		System.out.println("5. 계약기간: " + insurance.getContractPeriod());
		if (insurance instanceof Disease) {
			updateDiseaseInsurance(employee, insurance);
		} else if (insurance instanceof Injury) {
			updateInjuryInsurance(employee, insurance);
		} else if (insurance instanceof Automobile) {
			updateAutomobileInsurance(employee, insurance);
		}
	}

	// 2024-05-31 김대현
//  2024-06-04 김대현
	private void updateDiseaseInsurance(Employee employee, Insurance insurance) {
		// ProductManagementModel employee
		Disease diseaseInsurance = (Disease) insurance;
		System.out.println("6. 질병 최대 개수: " + diseaseInsurance.getDiseaseLimit());
		System.out.println("7. 질병 이름: " + diseaseInsurance.getDiseaseName());
		System.out.println("8. 수술 최대 횟수: " + diseaseInsurance.getSurgeriesLimit());

		try {
			int inputIndex = 0;
			input = scanner.next();
			inputIndex = Integer.parseInt(input);
			if (!(1 <= inputIndex && inputIndex <= 8)) {
				return;
			}
			System.out.println(inputIndex + "번에 해당하는 새로운 값: ");
			String inputParameter = scanner.next();

			do {
				System.out.println("1. 수정 2. 취소");
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					do {
						System.out.println("정말로 수정하시겠습니까?");
						System.out.println("1. 확인 2. 취소");
						input = scanner.next();
						index = Integer.parseInt(input);
						switch (index) {
						case 1:
							productManagementModel.updateInsuranceProduct(inputIndex, 
									inputParameter, diseaseInsurance, productList);
							System.out.println("수정되었습니다.");
							break;
						case 2:
							return;
						default:
							System.out.println("명시된 번호중에 클릭해주세요.");
						}
					} while (!(1 <= index && index <= 2));
					return;
				case 2:
					return;
				default:
					System.out.println("명시된 번호중에 클릭해주세요.");
				}
			} while (!(1 <= index && index <= 2));
		} catch (DuplicateInsuranceException | NotExistException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException numberFormatException) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
		}
	}

	// 2024-05-31 김대현
//  2024-06-04 김대현
	private void updateInjuryInsurance(Employee employee, Insurance insurance) {
		// ProductManagementModel employee
		Injury injuryInsurance = (Injury) insurance;
		System.out.println("6. 상해 보험 종류: " + injuryInsurance.getInjuryType().getName());
		System.out.println("7. 수술 최대 횟수: " + injuryInsurance.getSurgeriesLimit());

		try {
			int inputIndex = 0;
			do {
				input = scanner.next();
				inputIndex = Integer.parseInt(input);
				if (!(1 <= inputIndex && inputIndex <= 7)) {
					return;
				} else if (inputIndex == 6) {
					System.out.println("7. 상해 종류");
					System.out.println("1.경상 2. 중상");
				}
			} while (!(1 <= inputIndex && inputIndex <= 7));

			System.out.println(inputIndex + "번에 해당하는 새로운 값: ");
			String inputParameter = scanner.next();

			if (inputIndex == 6) {
				if (!(1 <= Integer.parseInt(inputParameter) && Integer.parseInt(inputParameter) <= 2)) {
					return;
				}
				;
			}

			do {
				System.out.println("1. 수정 2. 취소");
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					do {
						System.out.println("정말로 수정하시겠습니까?");
						System.out.println("1. 확인 2. 취소");
						input = scanner.next();
						index = Integer.parseInt(input);
						switch (index) {
						case 1:
							productManagementModel.updateInsuranceProduct(
									inputIndex, inputParameter, injuryInsurance, productList);
							System.out.println("수정되었습니다.");
							break;
						case 2:
							return;
						default:
							System.out.println("명시된 번호중에 클릭해주세요.");
						}
					} while (!(1 <= index && index <= 2));
					return;
				case 2:
					return;
				default:
					System.out.println("명시된 번호중에 클릭해주세요.");
				}
			} while (!(1 <= index && index <= 2));
		} catch (DuplicateInsuranceException | NotExistException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException numberFormatException) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
		}
	}

	// 2024-05-31 김대현
//  2024-06-04 김대현
	private void updateAutomobileInsurance(Employee employee, Insurance insurance) {
		// ProductManagementModel employee
		Automobile automobileInsurance = (Automobile) insurance;

		ServiceType serviceType = null;
		ArrayList<ServiceType> serviceTypeList = new ArrayList<ServiceType>();

		System.out.println("6. 사고 최대 횟수: " + automobileInsurance.getAccidentLimit());
		System.out.println("7. 차량 종류: " + automobileInsurance.getVehicleType().getName());
		System.out.println("8. 서비스 종류: " + automobileInsurance.getServiceList());

		try {
			int inputIndex = 0;
			do {
				input = scanner.next();
				inputIndex = Integer.parseInt(input);
				if (!(1 <= inputIndex && inputIndex <= 8)) {
					return;
				} else if (inputIndex == 7) {
					System.out.println("7. 차량 종류");
					System.out.println("1. 소형 2. 중형 3. 대형");
				} else if (inputIndex == 8) {
					boolean finish = false;
					while (!finish) {
						do {
							System.out.println("8. 서비스 종류");
							System.out.println("1.긴급견인 2. 긴급시동 3. 비상급유 4. 배터리충전 5. 엔진과열 수리 6. 타이어펑크 수리 ");
							System.out.println(inputIndex + "번에 해당하는 새로운 값: ");
							input = scanner.next();
							switch (Integer.parseInt(input)) {
							case 1:
								serviceType = ServiceType.EmergencyTowing;
								serviceTypeList.add(serviceType);
								break;
							case 2:
								serviceType = ServiceType.EmergencyStart;
								serviceTypeList.add(serviceType);
								break;
							case 3:
								serviceType = ServiceType.EmergencyRefueling;
								serviceTypeList.add(serviceType);
								break;
							case 4:
								serviceType = ServiceType.BatteryCharging;
								serviceTypeList.add(serviceType);
								break;
							case 5:
								serviceType = ServiceType.EngineOverheatingRepair;
								serviceTypeList.add(serviceType);
								break;
							case 6:
								serviceType = ServiceType.TirepunkRepair;
								serviceTypeList.add(serviceType);
								break;
							default:
								return;
							}
						} while (!(1 <= Integer.parseInt(input) && Integer.parseInt(input) <= 6));
						do {
							System.out.println("더 선택하시겠습니까?.");
							System.out.println("1. 예 2. 아니오");
							input = scanner.next();
							switch (Integer.parseInt(input)) {
							case 1:
								break;
							case 2:
								finish = true;
								break;
							default:
								System.out.println("명시된 번호 중에 클릭해주세요.");
								break;
							}
						} while (!(1 <= Integer.parseInt(input) && Integer.parseInt(input) <= 2));
					}

					do {
						System.out.println("1. 수정 2. 취소");
						input = scanner.next();
						index = Integer.parseInt(input);
						switch (index) {
						case 1:
							do {
								System.out.println("정말로 수정하시겠습니까?");
								System.out.println("1. 확인 2. 취소");
								input = scanner.next();
								index = Integer.parseInt(input);
								switch (index) {
								case 1:
									productManagementModel.updateInsuranceProduct(inputIndex, 
											null, automobileInsurance,
											serviceTypeList, productList);
									System.out.println("수정되었습니다.");
									break;
								case 2:
									return;
								default:
									System.out.println("명시된 번호중에 클릭해주세요.");
								}
							} while (!(1 <= index && index <= 2));
							return;
						case 2:
							return;
						default:
							System.out.println("명시된 번호중에 클릭해주세요.");
						}
					} while (!(1 <= index && index <= 2));
				}
			} while (!(1 <= Integer.parseInt(input) && Integer.parseInt(input) <= 9));

			System.out.println(inputIndex + "번에 해당하는 새로운 값: ");
			String inputParameter = scanner.next();

			if (inputIndex == 7) {
				if (!(1 <= Integer.parseInt(inputParameter) && Integer.parseInt(inputParameter) <= 3)) {
					return;
				}
				;
			}
			do {
				System.out.println("1. 수정 2. 취소");
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					do {
						System.out.println("정말로 수정하시겠습니까?");
						System.out.println("1. 확인 2. 취소");
						input = scanner.next();
						index = Integer.parseInt(input);
						switch (index) {
						case 1:
							productManagementModel.updateInsuranceProduct(inputIndex, 
									inputParameter, automobileInsurance, null, productList);
							System.out.println("수정되었습니다.");
							break;
						case 2:
							return;
						default:
							System.out.println("명시된 번호중에 클릭해주세요.");
						}
					} while (!(1 <= index && index <= 2));
					return;
				case 2:
					return;
				default:
					System.out.println("명시된 번호중에 클릭해주세요.");
				}
			} while (!(1 <= index && index <= 2));
		} catch (DuplicateInsuranceException | NotExistException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException numberFormatException) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
		}
	}

	// 2024-05-31 김대현
	private void deleteInsurance(Employee employee, int id) {
		// ProductManagementModel employee
		do {
			System.out.println("정말로 삭제하시겠습니까?");
			System.out.println("1. 확인 2. 취소");
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				try {
					productManagementModel.deleteInsuranceProduct(productList, id);
					System.out.println("삭제되었습니다.");
				} catch (NotExistException e) {
					System.out.println(e.getMessage());
				}
			case 2:
				return;
			default:
				System.out.println("명시된 번호중에 클릭해주세요.");
				break;
			}
		} while (!(1 <= index && index <= 2));
	}
	// TODO Underwriting
	private void showUnderwritingTask(Employee employee) {
		index = -1;
		boolean finish = false;
		while (!finish) {
			System.out.println(employee.getName() + "님! 처리하려는 일을 클릭해주세요.");
			System.out.println("1. 인수 심사");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					viewRequestingContract(employee);
					break;
				default:
					finish = true;
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			}
		}
		index = -1;
	}

	private void viewRequestingContract(Employee employee) {
		// UnderwritingModel employee
		ArrayList<Contract> contractList = this.contractList.getAllRequestingInsurance();
		// 여기
		do {
			System.out.println("\n=================");
			for (Contract contract : contractList) {
				Customer customer;
				try {
					customer = this.customerList.get(contract.getCustomerID());
				} catch (NotExistException e) {
					System.out.println(contract.getCustomerID() + " " + e.getMessage());
					continue;
				}
				String surgeryHistory = "";
				for (SurgeryHistory surgery : customer.getSurgeryHistoryList()) {
					surgeryHistory += "\n날짜 : " + surgery.getDate() + " 병원 이름 : " + surgery.getHospitalName();
				}
				String accidentHistory = "";
				for (AccidentHistory accident : customer.getAccidentHistoryList()) {
					accidentHistory += "\n날짜 : " + accident.getDate() + " 사고 내용 : " + accident.getAccidentDetail();
				}
				System.out.println("계약 번호 : " + contract.getId() + "\n심사 상태 : " + contract.getContractStatus().getText()
						+ " | 상품 번호 : " + contract.getProduct().getId() + "\n고객 이름 : " + customer.getName() + " | 전화번호 : "
						+ customer.getPhoneNumber() + " | 직업 : " + customer.getJob() + " | 나이 : " + customer.getAge()
						+ " | 성별 : " + customer.getGender().getName() + "\n주민등록번호 : "
						+ customer.getResidentRegistrationNumber() + " | 주소 : " + customer.getAddress() + " | 계좌 번호 : "
						+ customer.getBankAccount() + "\n사고 이력 : " + surgeryHistory + "\n수술 이력 : " + accidentHistory);
				System.out.println("-----------------");
			}
			System.out.println("=================\n");
			System.out.println("1. 심사 상태 콤보박스 2. 검색 3. 더블 클릭");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					contractList = getRequestingStatusContract();
					break;
				case 2:
					contractList = searchRequestingContract();
					break;
				case 3:
					doubleClickRequestingContract(employee, contractList);
					contractList = this.contractList.getAllRequestingInsurance();
					break;
				}
			} catch (NumberFormatException e) {
				//
			}
		} while (1 <= index && index <= 3);
	}

	private void doubleClickRequestingContract(Employee employee, ArrayList<Contract> contractList) {
		System.out.print("클릭한 계약 번호 : ");
		try {
			int id = Integer.parseInt(scanner.next());
			for (Contract contract : contractList) {
				if (contract.getId() == id) {
					selectRequestingContract(employee, contract);
					return;
				}
			}
			System.out.println("잘못된 값입니다.");
		} catch (NumberFormatException e) {
			System.out.println("잘못된 값입니다.");
		}
	}

	private void selectRequestingContract(Employee employee, Contract contract) {
		// UnderwritingModel employee
		Customer customer;
		try {
			customer = this.customerList.get(contract.getCustomerID());
			// 여기
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		String surgeryHistory = "";
		for (SurgeryHistory surgery : customer.getSurgeryHistoryList()) {
			surgeryHistory += "\n날짜 : " + surgery.getDate() + " 병원 이름 : " + surgery.getHospitalName();
		}
		String accidentHistory = "";
		for (AccidentHistory accident : customer.getAccidentHistoryList()) {
			accidentHistory += "\n날짜 : " + accident.getDate() + " 사고 내용 : " + accident.getAccidentDetail();
		}
		System.out.println("\n=================");
		System.out.println("계약 번호 : " + contract.getId() + "\n계약 상태 : " + contract.getContractStatus().getText()
				+ " | 상품 번호 : " + contract.getProduct().getId() + "\n고객 이름 : " + customer.getName() + " | 전화번호 : "
				+ customer.getPhoneNumber() + " | 직업 : " + customer.getJob() + " | 나이 : " + customer.getAge() + " | 성별 : "
				+ customer.getGender().getName() + "\n주민등록번호 : " + customer.getResidentRegistrationNumber() + " | 주소 : "
				+ customer.getAddress() + " | 계좌 번호 : " + customer.getBankAccount() + " | 재산 : " + customer.getProperty()
				+ "\n사고 이력 : " + surgeryHistory + "\n수술 이력 : " + accidentHistory);
		System.out.println("=================\n");
		try {
			System.out.println("1. 승인 2. 거절");
			int id = Integer.parseInt(scanner.next());
			switch (id) {
			case 1:
				if (underwritingModel.reviewAcquisition(contract, true, this.contractList))
					System.out.println("승인이 완료되었습니다.");
				break;
			case 2:
				if (underwritingModel.reviewAcquisition(contract, false, this.contractList))
					System.out.println("거절이 완료되었습니다.");
				break;
			}
		} catch (NumberFormatException e) {
			//
		} catch (NotExistContractException e) {
			System.out.println(e.getMessage());
		} catch (AlreadyProcessedException e) {
			System.out.println("이미 심사가 완료되었습니다.");
		}
	}
	private ArrayList<Contract> searchRequestingContract() {
		ArrayList<Contract> result = new ArrayList<>();
		System.out.print("ID 검색창 : ");
		try {
			int id = Integer.parseInt(scanner.next());
			result.add(this.contractList.get(id));
		} catch (NumberFormatException e) {
			//
		} catch (NotExistContractException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	private ArrayList<Contract> getRequestingStatusContract() {
		ArrayList<Contract> result = this.contractList.getAllRequestingInsurance();
		System.out.println("1. 미처리 2. 처리완료");
		try {
			int index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				result = this.contractList.getAllRequestingInsurance();
			case 2:
				result = this.contractList.getAllNotRequestingInsurance();
			}
		} catch (NumberFormatException e) {
			//
		}
		return result;
	}

//  2024-06-04 김대현
	// TODO CompensationPlanning
	private void showCompensationPlanningTask(Employee employee) {
		// 600110
		index = -1;
		boolean finish = false;
		while (!finish) {
			System.out.println(employee.getName() + "님! 처리하려는 일을 클릭해주세요.");
			System.out.println("1. 협력업체 평가 2. 협력업체 관리");
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				evaluatePartnerCompany(employee);
				break;
			case 2:
				managePartnerCompany(employee);
				break;
			default:
				finish = true;
				break;
			}
		}
		index = -1;
	}

	private void evaluatePartnerCompany(Employee employee) {
//		협력업체 정보 리스트 (협력업체 번호, 협력업체 이름, 협력업체 종류, 협력업체 전화번호)
		for (PartnerCompany e : partnerCompanyList.getAll()) {
			System.out.print("협력업체 번호: " + e.getId() + " ");
			System.out.print("협력업체 이름: " + e.getName() + " ");
			System.out.print("협력업체 종류: " + e.getPartnerCompanyType().getName() + " ");
			System.out.println("협력업체 전화번호: " + e.getPhoneNumber());
		}

		System.out.println("1. 검색 2. 더블클릭");
		try {
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				getPartnerCompany(employee);
				break;
			case 2:
				doubleClickPartnerCompanyForEvaluate(employee);
				break;
			default:
				return;
			}
		} catch (NumberFormatException numberFormatException) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			return;
		}

	}

	private void doubleClickPartnerCompanyForEvaluate(Employee employee) {
		System.out.println("협력업체의 번호(더블클릭): ");
		input = scanner.next();
		id = Integer.parseInt(input);
		PartnerCompany partnerCompany;
		try {
			partnerCompany = compensationPlanningModel.getPartnerCompany(partnerCompanyList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("1. 협력업체 번호: " + partnerCompany.getId());
		System.out.println("2. 협력업체 이름: " + partnerCompany.getName());
		System.out.println("3. 협력업체 전화번호: " + partnerCompany.getPhoneNumber());
		System.out.println("4. 협력업체 종류: " + partnerCompany.getPartnerCompanyType().getName());
		System.out.println("5. 대표자 이름: " + partnerCompany.getHeadName());
		System.out.println("6. 대표자 전화번호: " + partnerCompany.getHeadPhoneNumber());

		do {
			System.out.println("1. 평가 2. 취소");
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				selectPartnerCompanyForEvaluate(employee, partnerCompany);
				return;
			case 2:
				return;
			default:
				System.out.println("명시된 번호중에 클릭해주세요.");
			}
		} while (!(1 <= index && index <= 2));
	}

	private void selectPartnerCompanyForEvaluate(Employee employee, PartnerCompany partnerCompany) {
		// CompensationPlanningModel employee
		int evaluate;
		System.out.println("1. ★ 2. ★★ 3. ★★★ 4. ★★★★ 5. ★★★★★");
		input = scanner.next();
		evaluate = Integer.parseInt(input);
		switch (evaluate) {
		case 1:
			evaluate = 1;
			break;
		case 2:
			evaluate = 2;
			break;
		case 3:
			evaluate = 3;
			break;
		case 4:
			evaluate = 4;
			break;
		case 5:
			evaluate = 5;
			break;
		default:
			return;
		}
		try {
			compensationPlanningModel.evaluatePartnerCompany(evaluate, partnerCompany, this.partnerCompanyList);
			System.out.println("평가되었습니다.");
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
	}

	// 2024-06-04 김대현
	private void managePartnerCompany(Employee employee) {
		// 600110
		System.out.println("협력업체 정보 리스트");
//				협력업체 정보 리스트 (협력업체 번호, 협력업체 이름, 협력업체 종류, 협력업체 전화번호)

		for (PartnerCompany e : partnerCompanyList.getAll()) {
			System.out.print("협력업체 번호: " + e.getId() + " ");
			System.out.print("협력업체 이름: " + e.getName() + " ");
			System.out.print("협력업체 종류: " + e.getPartnerCompanyType().getName() + " ");
			System.out.println("협력업체 전화번호: " + e.getPhoneNumber());
		}

		System.out.println("1. 등록 2. 검색 3. 더블클릭");
		try {
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				createPartnerCompany(employee);
				break;
			case 2:
				getPartnerCompany(employee);
				break;
			case 3:
				doubleClickPartnerCompany(employee);
				break;
			default:
				return;
			}
		} catch (NumberFormatException numberFormatException) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			return;
		}

	}

//  2024-06-04 김대현
	private void createPartnerCompany(Employee employee) {
		// CompensationPlanningModel employee
		boolean finish = false;
		while (!finish) {
			try {
				System.out.println("1. 협력업체 이름:");
				input = scanner.next();
				String name = input;
				System.out.print("2. 담당자 전화번호: ");
				input = scanner.next();
				String phoneNumber = input;

				PartnerCompanyType partnerCompanyType = null;
				System.out.println("3. 협력업체 종류: ");
				System.out.println("1.병원 2. 카센터 3. 법무법인 4. 손해사정업체 5. 긴급출동회사");
				input = scanner.next();
				switch (Integer.parseInt(input)) {
				case 1:
					partnerCompanyType = PartnerCompanyType.Hospital;
					break;
				case 2:
					partnerCompanyType = PartnerCompanyType.RepairShop;
					break;
				case 3:
					partnerCompanyType = PartnerCompanyType.LawFirm;
					break;
				case 4:
					partnerCompanyType = PartnerCompanyType.DamageAssessmentCompany;
					break;
				case 5:
					partnerCompanyType = PartnerCompanyType.RoadsideAssistanceCompany;
					break;
				default:
					return;
				}

				System.out.println("4. 대표자 이름:");
				input = scanner.next();
				String headName = input;

				System.out.println("5. 대표자 전화번호:");
				input = scanner.next();
				String headPhoneNumber = input;

				do {
					System.out.println("1. 확인 2. 취소");
					input = scanner.next();
					index = Integer.parseInt(input);
					switch (index) {
					case 1:
						compensationPlanningModel.addPartnerCompany(
								name, phoneNumber, partnerCompanyType, headName,
								headPhoneNumber, partnerCompanyList);
						System.out.println("등록되었습니다.");
						finish = true;
						break;
					case 2:
						return;
					default:
						System.out.println("명시된 번호중에 클릭해주세요.");
					}
				} while (!(1 <= index && index <= 2));
			} catch (DuplicatePartnerCompanyException duplicatePartnerCompanyExeption) {
				System.out.println(duplicatePartnerCompanyExeption.getMessage());
				return;
			} catch (NumberFormatException numberFormatException) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
				return;
			}
		}

	}

//  2024-06-04 김대현
	private void getPartnerCompany(Employee employee) {
		// CompensationPlanningModel employee
		System.out.print("검색창: ");
		input = scanner.next();
		id = Integer.parseInt(input);
		PartnerCompany partnerCompany;
		try {
			partnerCompany = compensationPlanningModel.getPartnerCompany(partnerCompanyList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.print("협력업체 번호: " + partnerCompany.getId() + " ");
		System.out.print("협력업체 이름: " + partnerCompany.getName() + " ");
		System.out.print("협력업체 종류: " + partnerCompany .getPartnerCompanyType().getName() + " ");
		System.out.println("협력업체 전화번호: " + partnerCompany.getPhoneNumber());
	}

//  2024-06-04 김대현
	private void doubleClickPartnerCompany(Employee employee) {
		System.out.println("협력업체의 번호(더블클릭): ");
		input = scanner.next();
		id = Integer.parseInt(input);
		PartnerCompany partnerCompany;
		try {
			partnerCompany = compensationPlanningModel.getPartnerCompany(partnerCompanyList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("1. 협력업체 번호: " + partnerCompany.getId());
		System.out.println("2. 협력업체 이름: " + partnerCompany.getName());
		System.out.println("3. 협력업체 전화번호: " + partnerCompany.getPhoneNumber());
		System.out.println("4. 협력업체 종류: " + partnerCompany.getPartnerCompanyType().getName());
		System.out.println("5. 대표자 이름: " + partnerCompany.getHeadName());
		System.out.println("6. 대표자 전화번호: " + partnerCompany.getHeadPhoneNumber());

		System.out.println("1. 수정 2. 삭제");
		input = scanner.next();
		index = Integer.parseInt(input);
		switch (index) {
		case 1:
			updatePartnerCompany(employee, id);
			break;
		case 2:
			deletePartnerCompany(employee, id);
			break;
		default:
			return;
		}
	}

//  2024-06-04 김대현
	private void updatePartnerCompany(Employee employee, int id) {
		// CompensationPlanningModel employee
		PartnerCompany partnerCompany;
		try {
			partnerCompany = compensationPlanningModel.getPartnerCompany(partnerCompanyList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}

		System.out.println("협력업체 번호: " + partnerCompany.getId());
		System.out.println("협력업체 종류: " + partnerCompany.getPartnerCompanyType().getName());
		System.out.println("1. 협력업체 이름: " + partnerCompany.getName());
		System.out.println("2. 협력업체 전화번호: " + partnerCompany.getPhoneNumber());
		System.out.println("3. 대표자 이름: " + partnerCompany.getHeadName());
		System.out.println("4. 대표자 전화번호: " + partnerCompany.getHeadPhoneNumber());

		try {
			int inputIndex = 0;

			input = scanner.next();
			inputIndex = Integer.parseInt(input);
			if (!(1 <= inputIndex && inputIndex <= 4)) {
				return;
			}
			System.out.println(inputIndex + "번에 해당하는 새로운 값: ");
			String inputParameter = scanner.next();

			do {
				System.out.println("1. 수정 2. 취소");
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					do {
						System.out.println("정말로 수정하시겠습니까?");
						System.out.println("1. 확인 2. 취소");
						input = scanner.next();
						index = Integer.parseInt(input);
						switch (index) {
						case 1:
							compensationPlanningModel.updatePartnerCompany(
									inputIndex, inputParameter, partnerCompany,
									partnerCompanyList);
							System.out.println("수정되었습니다.");
							break;
						case 2:
							return;
						default:
							System.out.println("명시된 번호중에 클릭해주세요.");
						}
					} while (!(1 <= index && index <= 2));
					return;
				case 2:
					return;
				default:
					System.out.println("명시된 번호중에 클릭해주세요.");
				}
			} while (!(1 <= index && index <= 2));
		} catch (DuplicatePartnerCompanyException | NotExistException e) {
			System.out.println(e.getMessage());
		}
	}

//  2024-06-04 김대현
	private void deletePartnerCompany(Employee employee, int id) {
		// CompensationPlanningModel employee
		do {
			System.out.println("정말로 삭제하시겠습니까?");
			System.out.println("1. 확인 2. 취소");
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				try {
					compensationPlanningModel.deletePartnerCompany(partnerCompanyList, id);
					System.out.println("삭제되었습니다.");
				} catch (NotExistException e) {
					System.out.println(e.getMessage());
				}
				return;
			case 2:
				return;
			default:
				System.out.println("명시된 번호중에 클릭해주세요.");
			}
		} while (!(1 <= index && index <= 2));
	}

/////////////////////////////////////////////////////////////////
/////////////////////////////////계약관리팀//////////////////////////
	// TODO ContractManagement
	private void showContractManagementTask(Employee employee) {
		
		boolean finish = false;
		while (!finish) {
			System.out.println(employee.getName() + "님! 처리하려는 일을 클릭해주세요.");
			System.out.println("1. 분납/수금 처리 2. 제지급금 요청 3. 배서 심사 4. 만기 계약 심사 5. 부활 심사");
			try {
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					viewDefaultContract(employee);
					break;
				case 2:
					viewTerminatingContract(employee);
					break;
				case 3:
					viewEndorsementContract(employee);
					break;
				case 4:
					viewReContract(employee);
					break;
				case 5:
					viewRevival(employee);
					break;
				default:
					finish = true;
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			}
		}
		index = -1;
	}

	private void viewDefaultContract(Employee employee) {
		int index = 0;
		do {
			ArrayList<Contract> contractList = this.contractList.getAllDefaultContract();
			for (Contract contract : contractList) {
				Customer customer;
				try {
					customer = this.customerList.get(contract.getCustomerID());
				} catch (NotExistException e) {
					System.out.println(contract.getCustomerID() + " " + e.getMessage());
					continue;
				}
				System.out.println("<고객 정보>");
				System.out.println(" 고객 이름 : " + customer.getName() + " 전화번호 : " + customer.getPhoneNumber() + " 직업 : "
						+ customer.getJob() + " 나이 : " + customer.getAge() + " 성별 : " + customer.getGender().getName()
						+ " 주민등록번호 : " + customer.getResidentRegistrationNumber() + " 주소 : " + customer.getAddress()
						+ " 계좌 번호 : " + customer.getBankAccount() + " 재산 : " + customer.getProperty() + "\n사고 이력 : "
						+ customer.getAccidentHistoryList() + "\n수술 이력 : " + customer.getSurgeryHistoryList());
				System.out.println("<계약 정보>");
				System.out.println(" 계약 번호 : " + contract.getId() + " 상품 번호 : " + contract.getProduct().getId()
						+ " 납부날짜 : " + contract.getLastPaidDate());
			}
			System.out.println("1. 검색 2. 더블 클릭");
			index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				contractList = searchStatusDefaultContract();
				break;
			case 2:
				doubleClickDefaultContract(employee);
				break;
			}
		} while (1 <= index && index <= 2);
	}

	private ArrayList<Contract> searchStatusDefaultContract() {
		try {
			ArrayList<Contract> result = new ArrayList<>();
			System.out.println("ID 검색창 : ");
			int id = Integer.parseInt(scanner.next());
			Contract contract = this.contractList.get(id);
			result.add(contract);
			return result;
		} catch (NumberFormatException e) {
			//
		} catch (NotExistContractException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	private void doubleClickDefaultContract(Employee employee) {
		// ContractManagementModel employee
		try {
			System.out.println("해지 계약 번호(더블클릭): ");
			int id = Integer.parseInt(scanner.next());
			Termination contract = this.terminationList.get(id);
			Customer customer = this.customerList.get(contract.getCustomerID());
			System.out.println("<고객 정보>");
			System.out.println(" 고객 이름 : " + customer.getName() + " 전화번호 : " + customer.getPhoneNumber() + " 직업 : "
					+ customer.getJob() + " 나이 : " + customer.getAge() + " 성별 : " + customer.getGender().getName()
					+ " 주민등록번호 : " + customer.getResidentRegistrationNumber() + " 주소 : " + customer.getAddress()
					+ " 계좌 번호 : " + customer.getBankAccount() + " 재산 : " + customer.getProperty() + "\n사고 이력 : "
					+ customer.getAccidentHistoryList() + "\n수술 이력 : " + customer.getSurgeryHistoryList());
			System.out.println("<계약 정보>");
			System.out.println(" 계약 번호 : " + contract.getId() + " 상품 번호 : " + contract.getProduct().getId() + " 납부날짜 : "
					+ contract.getLastPaidDate());
			do {
				System.out.println("1. 안내장 발송  2. 취소");
				int index = Integer.parseInt(scanner.next());
				switch (index) {
				case 1:
					System.out.println("안내장 발송이 완료되었습니다.");
					break;
				case 2:
					break;
				default:
					System.out.println("명시된 번호중에 클릭해주세요.");
				}
			} while (!(1 <= index && index <= 2));
		} catch (NumberFormatException e) {

		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
	}

///////////////////////////해지 심사/////////////////////////////
	private void viewTerminatingContract(Employee employee) {
		int index = 0;
		do {
			ArrayList<Termination> terminationList = this.terminationList.getAllTerminatingContract();
			for (Termination contract : terminationList) {
				Customer customer;
				try {
					customer = this.customerList.get(contract.getCustomerID());
				} catch (NotExistException e) {
					System.out.println(contract.getCustomerID() + " " + e.getMessage());
					continue;
				}
				System.out.println("<고객 정보>");
				System.out.println(" 고객 이름 : " + customer.getName() + " 전화번호 : " + customer.getPhoneNumber() + " 직업 : "
						+ customer.getJob() + " 나이 : " + customer.getAge() + " 성별 : " + customer.getGender().getName()
						+ " 주민등록번호 : " + customer.getResidentRegistrationNumber() + " 주소 : " + customer.getAddress()
						+ " 계좌 번호 : " + customer.getBankAccount() + " 재산 : " + customer.getProperty() + "\n사고 이력 : "
						+ customer.getAccidentHistoryList() + "\n수술 이력 : " + customer.getSurgeryHistoryList());
				System.out.println("<계약 정보>");
				System.out.println(" 계약 번호 : " + contract.getId() + " 상품 번호 : " + contract.getProduct().getId()
						+ " 해지 날짜 : " + contract.getApplyDate() + " 접수 상태 : " + contract.getContractStatus().getText());
			}
			System.out.println("1. 접수 상태 콤보박스 2. 검색 3. 더블 클릭");
			index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				terminationList = getStatusTerminatingContract();
				break;
			case 2:
				terminationList = searchTerminatingContract();
				break;
			case 3:
				doubleClickTerminatingContract(employee);
				break;
			}
		} while (1 <= index && index <= 2);
	}

	private ArrayList<Termination> searchTerminatingContract() {
		try {
			ArrayList<Termination> result = new ArrayList<>();
			System.out.println("ID 검색창 : ");
			int id = Integer.parseInt(scanner.next());
			Termination contract = this.terminationList.getTerminatingContractById(id);
			result.add(contract);
			return result;
		} catch (NumberFormatException e) {
//
		} catch (IllegalArgumentException e) {
			System.out.println("해당 계약 정보가 존재하지 않습니다.");
		}
		return null;
	}

	private ArrayList<Termination> getStatusTerminatingContract() {
		System.out.println("1. 미처리 2. 처리완료");
		int index = Integer.parseInt(scanner.next());
		ArrayList<Termination> result = this.terminationList.getAllTerminatingContract();
		switch (index) {
		case 1:
			result = this.terminationList.getAllUnprocessedTerminatingContract();
			break;
		case 2:
			result = this.terminationList.getAllProcessedTerminatingContract();
			break;
		}
		return result;
	}

	private void doubleClickTerminatingContract(Employee employee) {
		// ContractManagementModel employee
		try {
			System.out.println("해지 계약 번호(더블클릭): ");
			int id = Integer.parseInt(scanner.next());
			Termination contract = this.terminationList.get(id);
			Customer customer = this.customerList.get(contract.getCustomerID());
			System.out.println("<고객 정보>");
			System.out.println(" 고객 이름 : " + customer.getName() + " 전화번호 : " + customer.getPhoneNumber() + " 직업 : "
					+ customer.getJob() + " 나이 : " + customer.getAge() + " 성별 : " + customer.getGender().getName()
					+ " 주민등록번호 : " + customer.getResidentRegistrationNumber() + " 주소 : " + customer.getAddress()
					+ " 계좌 번호 : " + customer.getBankAccount() + " 재산 : " + customer.getProperty() + "\n사고 이력 : "
					+ customer.getAccidentHistoryList() + "\n수술 이력 : " + customer.getSurgeryHistoryList());
			System.out.println("<계약 정보>");
			System.out.println("계약 번호 : " + contract.getId() + " 상품 번호 : " + contract.getProduct().getId() + " 해지 날짜 : "
					+ contract.getTerminationDate() + " 제지급 금액 : " + contract.getTerminationFee() + " 심사 상태 : "
					+ contract.getTerminationStatus().getText());

			do {
				System.out.println("1. 요청  2. 취소");
				index = Integer.parseInt(scanner.next());
				switch (index) {
				case 1:
					if (contractManagementModel.requestTerminationFee(contract, customer, 
							this.paymentDetailList, this.contractList))
						System.out.println("<계약 정보>");
					System.out.println("계약 번호 : " + contract.getId() + " 상품 번호 : " + contract.getProduct().getId()
							+ " 해지 날짜 : " + contract.getTerminationDate() + " 제지급 금액 : " + contract.getTerminationFee()
							+ " 심사 상태 : " + contract.getTerminationStatus().getText());
					System.out.println("신청되었습니다.");
					break;
				case 2:
					return;
				default:
					System.out.println("명시된 번호중에 클릭해주세요.");
				}
			} while (!(1 <= index && index <= 2));
		} catch (NumberFormatException e) {

		} catch (NotExistContractException | NotExistException e) {
			System.out.println(e.getMessage());
		} catch (AlreadyProcessedException e) {
			System.out.println(e.getMessage());
		}
	}

///////////////////////////배서 심사////////////////////////////////
	private void viewEndorsementContract(Employee employee) {
		int index = 0;
		do {
			ArrayList<Endorsement> result = this.endorsementList.getAllEndorsementContract();
			for (Endorsement contract : result) {
				Customer customer;
				try {
					customer = this.customerList.get(contract.getCustomerID());
				} catch (NotExistException e) {
					System.out.println(contract.getCustomerID() + " " + e.getMessage());
					continue;
				}
				System.out.println("<고객 정보>");
				System.out.println(" 고객 이름 : " + customer.getName() + " 전화번호 : " + customer.getPhoneNumber() + " 직업 : "
						+ customer.getJob() + " 나이 : " + customer.getAge() + " 성별 : " + customer.getGender().getName()
						+ " 주민등록번호 : " + customer.getResidentRegistrationNumber() + " 주소 : " + customer.getAddress()
						+ " 계좌 번호 : " + customer.getBankAccount() + " 재산 : " + customer.getProperty() + "\n사고 이력 : "
						+ customer.getAccidentHistoryList() + "\n수술 이력 : " + customer.getSurgeryHistoryList());
				System.out.println("<계약 정보>");
				System.out.println(
						"계약 번호 : " + contract.getId() + " 상품 번호 : " + contract.getProduct().getId() + "신청 날짜 : "
								+ contract.getApplyDate() + " 심사 상태 : " + contract.getEndorsementStatus().getText());
			}
			System.out.println("1. 접수 상태 콤보박스 2.더블 클릭 3. 요청");
			index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				result = getStatusEndorsementContract();
				break;
			case 2:
				doubleClickEndorsementContract(employee);
				break;
			case 3:

				break;
			}
		} while (1 <= index && index <= 2);
	}

	private ArrayList<Endorsement> getStatusEndorsementContract() {
		System.out.println("1. 미처리 2. 처리완료");
		int index = Integer.parseInt(scanner.next());
		ArrayList<Endorsement> result = this.endorsementList.getAllEndorsementContract();
		switch (index) {
		case 1:
			result = this.endorsementList.getAllUnprocessedEndorsementContract();
			break;
		case 2:
			result = this.endorsementList.getAllProcessedEndorsementContract();
			break;
		}
		return result;
	}

	private void doubleClickEndorsementContract(Employee employee) {
		// ContractManagementModel employee
		try {
			System.out.println("배서 계약 번호(더블클릭): ");
			int id = Integer.parseInt(scanner.next());
			Endorsement contract = this.endorsementList.get(id);
			Customer customer = this.customerList.get(contract.getCustomerID());
			System.out.println("<고객 정보>");
			System.out.println(" 고객 이름 : " + customer.getName() + " 전화번호 : " + customer.getPhoneNumber() + " 직업 : "
					+ customer.getJob() + " 나이 : " + customer.getAge() + " 성별 : " + customer.getGender().getName()
					+ " 주민등록번호 : " + customer.getResidentRegistrationNumber() + " 주소 : " + customer.getAddress()
					+ " 계좌 번호 : " + customer.getBankAccount() + " 재산 : " + customer.getProperty() + "\n사고 이력 : "
					+ customer.getAccidentHistoryList() + "\n수술 이력 : " + customer.getSurgeryHistoryList());
			System.out.println("<계약 정보>");
			System.out.println("계약 번호 : " + contract.getId() + " 상품 번호 : " + contract.getProduct().getId() + "신청 날짜 : "
					+ contract.getApplyDate() + " 심사 상태 : " + contract.getEndorsementStatus().getText());
			System.out.println("<배서 정보>");
			System.out.println(contract.getPaymentDate());
			System.out.println("1. 승인   2. 거절");
			int index = Integer.parseInt(scanner.next());
			if (contractManagementModel.reviewEndorsement(contract, customer, index)) {
				System.out.println("요청이 완료되었습니다.");
			}
		} catch (NumberFormatException e) {

		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
	}

//////////////////////////////////만기 재계약 심사/////////////////////////////////
	private void viewReContract(Employee employee) {
		int index = 0;
		do {
			ArrayList<Recontract> contractList = this.recontractList.getAllReContract();
			for (Recontract contract : contractList) {
				Customer customer;
				try {
					customer = this.customerList.get(contract.getCustomerID());
				} catch (NotExistException e) {
					System.out.println(contract.getCustomerID() + " " + e.getMessage());
					continue;
				}
				System.out.println("<고객 정보>");
				System.out.println(" 고객 이름 : " + customer.getName() + " 전화번호 : " + customer.getPhoneNumber() + " 직업 : "
						+ customer.getJob() + " 나이 : " + customer.getAge() + " 성별 : " + customer.getGender().getName()
						+ " 주민등록번호 : " + customer.getResidentRegistrationNumber() + " 주소 : " + customer.getAddress()
						+ " 계좌 번호 : " + customer.getBankAccount() + " 재산 : " + customer.getProperty() + "\n사고 이력 : "
						+ customer.getAccidentHistoryList() + "\n수술 이력 : " + customer.getSurgeryHistoryList());
				System.out.println("<계약 정보>");
				System.out.println("계약 번호 : " + contract.getId() + " 상품 번호 : " + contract.getProduct().getId()
						+ "만기 날짜 : " + contract.getExpirationDate() + " 심사 상태 : "
						+ contract.getRecontractStatus().getText());
			}
			System.out.println("1. 접수 상태 콤보박스 2. 검색 3. 더블 클릭");
			index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				contractList = getStatusReContract();
				break;
			case 2:
				contractList = searchReContract();
				break;
			case 3:
				doubleClickReContract(employee);
				break;
			}
		} while (1 <= index && index <= 2);
	}

	private ArrayList<Recontract> getStatusReContract() {
		System.out.println("1. 미처리 2. 처리완료");
		int index = Integer.parseInt(scanner.next());
		ArrayList<Recontract> result = this.recontractList.getAllReContract();
		switch (index) {
		case 1:
			result = this.recontractList.getAllUnprocessedReContract();
			break;
		case 2:
			result = this.recontractList.getAllProcessedReContract();
			break;
		}
		return result;
	}

	private ArrayList<Recontract> searchReContract() {
		try {
			ArrayList<Recontract> result = new ArrayList<>();
			System.out.println("ID 검색창 : ");
			int id = Integer.parseInt(scanner.next());
			Recontract contract = this.recontractList.getReContractById(id);
			result.add(contract);
			return result;
		} catch (NumberFormatException e) {
//
		} catch (IllegalArgumentException e) {
			System.out.println("해당 계약 정보가 존재하지 않습니다.");
		}
		return null;
	}

	private void doubleClickReContract(Employee employee) {
		// ContractManagementModel employee
		try {
			System.out.println("재계약신청 계약 번호(더블클릭): ");
			int id = Integer.parseInt(scanner.next());
			Recontract contract = this.recontractList.get(id);
			Customer customer = this.customerList.get(contract.getCustomerID());
			System.out.println("<고객 정보>");
			System.out.println(" 고객 이름 : " + customer.getName() + " 전화번호 : " + customer.getPhoneNumber() + " 직업 : "
					+ customer.getJob() + " 나이 : " + customer.getAge() + " 성별 : " + customer.getGender().getName()
					+ " 주민등록번호 : " + customer.getResidentRegistrationNumber() + " 주소 : " + customer.getAddress()
					+ " 계좌 번호 : " + customer.getBankAccount() + " 재산 : " + customer.getProperty() + "\n사고 이력 : "
					+ customer.getAccidentHistoryList() + "\n수술 이력 : " + customer.getSurgeryHistoryList());
			System.out.println("<계약 정보>");
			System.out.println("계약 번호 : " + contract.getId() + " 상품 번호 : " + contract.getProduct().getId() + "만기 날짜 : "
					+ contract.getExpirationDate() + " 심사 상태 : " + contract.getRecontractStatus().getText());
			System.out.println("1. 승인  2. 거절");
			int index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				if (contractManagementModel.reviewRecontract(this.contractList, contract, customer, 1))
					System.out.println("승인이 완료되었습니다.");
				break;
			case 2:
				if (contractManagementModel.reviewRecontract(this.contractList, contract, customer, 2))
					System.out.println("거절이 완료되었습니다.");
				break;
			}

		} catch (NumberFormatException e) {

		} catch (NotExistException | NotExistContractException e) {
			System.out.println(e.getMessage());
		}
	}

//////////////////////////////////////부활 심사////////////////////////////////
	private void viewRevival(Employee employee) {
		int index = 0;
		do {
			ArrayList<Revival> contractList = this.revivalList.getAllRevivalContract();
			for (Revival contract : contractList) {
				Customer customer;
				try {
					customer = this.customerList.get(contract.getCustomerID());
				} catch (NotExistException e) {
					System.out.println(contract.getCustomerID() + " " + e.getMessage());
					continue;
				}
				System.out.println("<고객 정보>");
				System.out.println(" 고객 이름 : " + customer.getName() + " 전화번호 : " + customer.getPhoneNumber() + " 직업 : "
						+ customer.getJob() + " 나이 : " + customer.getAge() + " 성별 : " + customer.getGender().getName()
						+ " 주민등록번호 : " + customer.getResidentRegistrationNumber() + " 주소 : " + customer.getAddress()
						+ " 계좌 번호 : " + customer.getBankAccount() + " 재산 : " + customer.getProperty() + "\n사고 이력 : "
						+ customer.getAccidentHistoryList() + "\n수술 이력 : " + customer.getSurgeryHistoryList());
				System.out.println("<계약 정보>");
				System.out.println(
						"계약 번호 : " + contract.getId() + " 상품 번호 : " + contract.getProduct().getId() + " 정지 날짜 : "
								+ contract.getTerminationDate() + " 심사 상태 : " + contract.getRevivalStatus().getText());
			}
			System.out.println("1. 접수 상태 콤보박스 2. 검색 3. 더블 클릭");
			index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				contractList = getStatusRevival();
				break;
			case 2:
				contractList = searchRevivalContract();
				break;
			case 3:
				doubleClickRevivalContract(employee);
				break;
			}
		} while (1 <= index && index <= 2);
	}

	private ArrayList<Revival> searchRevivalContract() {
		try {
			ArrayList<Revival> result = new ArrayList<>();
			System.out.println("ID 검색창 : ");
			int id = Integer.parseInt(scanner.next());
			Revival contract = this.revivalList.getRevivalById(id);
			result.add(contract);
			return result;
		} catch (NumberFormatException e) {
//
		} catch (IllegalArgumentException e) {
			System.out.println("해당 계약 정보가 존재하지 않습니다.");
		}
		return null;
	}

	private ArrayList<Revival> getStatusRevival() {
		System.out.println("1. 미처리 2. 처리완료");
		int index = Integer.parseInt(scanner.next());
		ArrayList<Revival> result = this.revivalList.getAllRevivalContract();
		switch (index) {
		case 1:
			result = this.revivalList.getAllUnprocessedRevival();
			break;
		case 2:
			result = this.revivalList.getAllProcessedRevival();
			break;
		}
		return result;
	}

	private void doubleClickRevivalContract(Employee employee) {
		// ContractManagementModel employee
		try {
			System.out.println("부활 계약 번호(더블클릭): ");
			int id = Integer.parseInt(scanner.next());
			Revival contract = this.revivalList.get(id);
			Customer customer = this.customerList.get(contract.getCustomerID());
			System.out.println("<고객 정보>");
			System.out.println(" 고객 이름 : " + customer.getName() + " 전화번호 : " + customer.getPhoneNumber() + " 직업 : "
					+ customer.getJob() + " 나이 : " + customer.getAge() + " 성별 : " + customer.getGender().getName()
					+ " 주민등록번호 : " + customer.getResidentRegistrationNumber() + " 주소 : " + customer.getAddress()
					+ " 계좌 번호 : " + customer.getBankAccount() + " 재산 : " + customer.getProperty() + "\n사고 이력 : "
					+ customer.getAccidentHistoryList() + "\n수술 이력 : " + customer.getSurgeryHistoryList());
			System.out.println("<계약 정보>");
			System.out.println("계약 번호 : " + contract.getId() + " 상품 번호 : " + contract.getProduct().getId() + " 정지 날짜 : "
					+ contract.getExpirationDate() + " 심사 상태 : " + contract.getRevivalStatus().getText());
			System.out.println("1. 승인  2. 거절");
			int index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				if (contractManagementModel.reviewRevival(this.contractList, contract, customer, 1))
					System.out.println("신청되었습니다.");
				break;
			case 2:
				if (contractManagementModel.reviewRevival(this.contractList, contract, customer, 2))
					System.out.println("신청되었습니다.");
				break;
			}

		} catch (NumberFormatException e) {

		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
	}

//	2024-06-02 김대현
	// TODO LoanManagement
	private void showLoanManagementTask(Employee employee) {
		// 600090
		index = -1;
		boolean finish = false;
		while (!finish) {
			System.out.println(employee.getName() + "님! 처리하려는 일을 클릭해주세요.");
			System.out.println("1. 대출 상품 관리 2.대출금 요청 3. 대출 원리금 수금");
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				manageLoanProduct(employee);
				break;
			case 2:
				viewRequestingLoan(employee);
				break;
			case 3:
				collectLoanPrincipalInterest(employee);
				break;
			default:
				finish = true;
				break;
			}
		}
		index = -1;
	}

	private void collectLoanPrincipalInterest(Employee employee) {
		ArrayList<Contract> contractList = this.contractList.getAllNotRequestingLoan();
		do {
			for (Contract contract : contractList) {
				Customer customer;
				try {
					customer = this.customerList.get(contract.getCustomerID());
				} catch (NotExistException e) {
					System.out.println(contract.getCustomerID() + " " + e.getMessage());
					continue;
				}
				String surgeryHistory = "";
				for (SurgeryHistory surgery : customer.getSurgeryHistoryList()) {
					surgeryHistory += "\n날짜 : " + surgery.getDate() + " 병원 이름 : " + surgery.getHospitalName();
				}
				String accidentHistory = "";
				for (AccidentHistory accident : customer.getAccidentHistoryList()) {
					accidentHistory += "\n날짜 : " + accident.getDate() + " 사고 내용 : " + accident.getAccidentDetail();
				}
				System.out.println("고객 번호 : " + customer.getId() + " 고객 이름 : " + customer.getName() + " 전화번호 : "
						+ customer.getPhoneNumber() + " 직업 : " + customer.getJob() + " 나이 : " + customer.getAge()
						+ " 성별 : " + customer.getGender().getName() + " 주민등록번호 : "
						+ customer.getResidentRegistrationNumber() + " 주소 : " + customer.getAddress() + " 계좌 번호 : "
						+ customer.getBankAccount() + " 재산 : " + customer.getProperty() + " 마지막 입금 날짜 : "
						+ contract.getLastPaidDate() + "\n사고 이력 : " + surgeryHistory + "\n수술 이력 : " + accidentHistory);
				System.out.println("원리금 : " + contract.getOutcome());
			}
			System.out.println("1. 검색 2. 더블클릭");
			try {
				int index = Integer.parseInt(scanner.next());
				switch (index) {
				case 1:
					contractList = searchNotRequestingLoan();
					break;
				case 2:
					doubleClickedNotRequestingLoan(employee);
					break;
				}
			} catch (NumberFormatException e) {
				//
			}
		} while (1 <= index && index <= 2);
	}

	private ArrayList<Contract> searchNotRequestingLoan() {
		ArrayList<Contract> result = new ArrayList<>();
		System.out.print("ID 검색창 : ");
		try {
			int id = Integer.parseInt(scanner.next());
			result.add(this.contractList.getNotRequestingLoanByCustomer(id));
		} catch (NumberFormatException e) {
			//
		} catch (NotExistContractException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	private void doubleClickedNotRequestingLoan(Employee employee) {
		System.out.print("클릭한 고객 번호 : ");
		try {
			int id = Integer.parseInt(scanner.next());
			Contract contract = this.contractList.getNotRequestingLoanByCustomer(id);
			selectNotRequestingLoan(employee, contract);
		} catch (NumberFormatException e) {
			//
		} catch (NotExistContractException e) {
			System.out.println(e.getMessage());
		}
	}

	private void selectNotRequestingLoan(Employee employee, Contract contract) {
		// LoanManagementModel employee
		Customer customer;
		try {
			customer = this.customerList.get(contract.getCustomerID());
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		String surgeryHistory = "";
		for (SurgeryHistory surgery : customer.getSurgeryHistoryList()) {
			surgeryHistory += "\n날짜 : " + surgery.getDate() + " 병원 이름 : " + surgery.getHospitalName();
		}
		String accidentHistory = "";
		for (AccidentHistory accident : customer.getAccidentHistoryList()) {
			accidentHistory += "\n날짜 : " + accident.getDate() + " 사고 내용 : " + accident.getAccidentDetail();
		}
		System.out.println(" 고객 이름 : " + customer.getName() + " 전화번호 : " + customer.getPhoneNumber() + " 직업 : "
				+ customer.getJob() + " 나이 : " + customer.getAge() + " 성별 : " + customer.getGender().getName()
				+ " 주민등록번호 : " + customer.getResidentRegistrationNumber() + " 주소 : " + customer.getAddress()
				+ " 계좌 번호 : " + customer.getBankAccount() + " 재산 : " + customer.getProperty() + "\n사고 이력 : "
				+ surgeryHistory + "\n수술 이력 : " + accidentHistory);
		try {
			System.out.println("1. 안내장 발송 2. 취소");
			index = Integer.parseInt(scanner.next());
			do {
				switch (index) {
				case 1:
					loanManagementModel.collectLoanPrincipalInterest();
					System.out.println("안내장이 발송되었습니다.");
					break;
				case 2:
					break;
				case 3:
					System.out.println("명시된 번호중에 클릭해주세요.");
				}
			} while (!(1 <= index && index <= 2));
		} catch (NumberFormatException e) {
			//
		}
	}

	private void viewRequestingLoan(Employee employee) {
		// LoanManagementModel employee
		ArrayList<Contract> contractList = this.contractList.getAllLoanContract();
		do {
			for (Contract contract : contractList) {
				Customer customer;
				try {
					customer = this.customerList.get(contract.getCustomerID());
				} catch (NotExistException e) {
					System.out.println(contract.getCustomerID() + " " + e.getMessage());
					continue;
				}
				Loan product = (Loan) contract.getProduct();
				System.out.println("계약 번호 : " + contract.getId() + " 처리 상태 : " + contract.getContractStatus().getText()
						+ " 상품 번호 : " + product.getId() + " 상품 이름 : " + product.getName() + " 대출 종류 : "
						+ product.getLoanType().getName() + " 이자율 : " + product.getInterestRate() + " 대출 가능 최대 금액 : ");
				String surgeryHistory = "";
				for (SurgeryHistory surgery : customer.getSurgeryHistoryList()) {
					surgeryHistory += "\n날짜 : " + surgery.getDate() + " 병원 이름 : " + surgery.getHospitalName();
				}
				String accidentHistory = "";
				for (AccidentHistory accident : customer.getAccidentHistoryList()) {
					accidentHistory += "\n날짜 : " + accident.getDate() + " 사고 내용 : " + accident.getAccidentDetail();
				}
				System.out.println(" 고객 이름 : " + customer.getName() + " 전화번호 : " + customer.getPhoneNumber() + " 직업 : "
						+ customer.getJob() + " 나이 : " + customer.getAge() + " 성별 : " + customer.getGender().getName()
						+ " 주민등록번호 : " + customer.getResidentRegistrationNumber() + " 주소 : " + customer.getAddress()
						+ " 계좌 번호 : " + customer.getBankAccount() + " 재산 : " + customer.getProperty() + "\n사고 이력 : "
						+ surgeryHistory + "\n수술 이력 : " + accidentHistory);
			}
			System.out.println("1. 처리 상태 콤보박스 2. 검색 3. 더블 클릭 4. 요청");
			try {
				int index = Integer.parseInt(scanner.next());
				switch (index) {
				case 1:
					contractList = getRequestingStatusLoan();
					break;
				case 2:
					contractList = searchRequestingLoan();
					break;
				case 3:
					doubleClickRequestingLoan(employee, contractList);
					break;
				case 4:
					requestLoan(employee, contractList);
					break;
				}
			} catch (NumberFormatException e) {
				//
			}
		} while (1 <= index && index <= 3);
	}

	private ArrayList<Contract> searchRequestingLoan() {
		ArrayList<Contract> result = new ArrayList<>();
		System.out.print("ID 검색창 : ");
		try {
			int id = Integer.parseInt(scanner.next());
			result.add(this.contractList.getLoanContract(id));
		} catch (NumberFormatException e) {
			//
		} catch (NotExistContractException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	private ArrayList<Contract> getRequestingStatusLoan() {
		ArrayList<Contract> result = this.contractList.getAllLoanContract();
		System.out.println("1. 미처리 2. 처리완료");
		try {
			int index = Integer.parseInt(scanner.next());
			switch (index) {
			case 1:
				result = this.contractList.getAllRequestingLoan();
			case 2:
				result = this.contractList.getAllNotRequestingLoan();
			}
		} catch (NumberFormatException e) {
			//
		}
		return result;
	}

	private void requestLoan(Employee employee, ArrayList<Contract> contractList) {
		// LoanManagementModel employee
		System.out.print("클릭한 계약 번호 : ");
		Contract selectedContract = null;
		try {
			int id = Integer.parseInt(scanner.next());
			for (Contract contract : contractList) {
				if (contract.getId() == id) {
					selectedContract = contract;
					break;
				}
			}
			if (selectedContract == null) {
				System.out.println("잘못된 값입니다.");
				return;
			}

			do {
				System.out.println("1. 승인 2. 거절 3. 취소");
				index = Integer.parseInt(scanner.next());
				Customer customer = this.customerList.get(selectedContract.getCustomerID());
				switch (index) {
				case 1:
					System.out.print("지급 금액 : ");
					int money = Integer.parseInt(scanner.next());
					PaymentType paymentType = getPaymentType();
					loanManagementModel.requestLoan(selectedContract, customer, money, paymentType, true, this.contractList,
							this.paymentDetailList, this.compensationDetailList);
					break;
				case 2:
					loanManagementModel.requestLoan(selectedContract, customer, 0, null, false, this.contractList,
							this.paymentDetailList, this.compensationDetailList);
					break;
				case 3:
					break;
				default:
					System.out.println("명시된 번호중에 클릭해주세요.");
				}
			} while (!(1 <= index && index <= 3));
		} catch (NumberFormatException e) {
			//
		} catch (AlreadyProcessedException | NotExistContractException | NotExistException e) {
			System.out.println(e.getMessage());
		}
	}

	private void doubleClickRequestingLoan(Employee employee, ArrayList<Contract> contractList) {
		System.out.print("클릭한 계약 번호 : ");
		try {
			int id = Integer.parseInt(scanner.next());
			for (Contract contract : contractList) {
				if (contract.getId() == id) {
					selectRequestingLoan(employee, contract);
					return;
				}
			}
			System.out.println("잘못된 값입니다.");
		} catch (NumberFormatException e) {
			System.out.println("잘못된 값입니다.");
		}
	}

	private void selectRequestingLoan(Employee employee, Contract contract) {
		// LoanManagementModel employee
		Customer customer;
		try {
			customer = this.customerList.get(contract.getCustomerID());
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		Loan product = (Loan) contract.getProduct();
		System.out.print("계약 번호 : " + contract.getId() + " 처리 상태 : " + contract.getContractStatus().getText()
				+ " 상품 번호 : " + product.getId() + " 상품 이름 : " + product.getName() + " 대출 종류 : "
				+ product.getLoanType().getName() + " 이자율 : " + product.getInterestRate() + " 대출 가능 최대 금액 : "
				+ product.getLimit() + " 최소 자산 : " + product.getMinimumAsset());
		if (product instanceof Collateral) {
			System.out.println(" 담보 종류 : " + ((Collateral) product).getCollateralType().getName() + " 담보 최소 가치 : "
					+ ((Collateral) product).getMinimumValue());
		} else if (product instanceof FixedDeposit) {
			System.out.println(" 최소 예치 금액 : " + ((FixedDeposit) product).getMinimumAmount());
		} else if (product instanceof InsuranceContract) {
			System.out.println(" 대상 상품 ID : " + ((InsuranceContract) product).getProductID());
		}
		String surgeryHistory = "";
		for (SurgeryHistory surgery : customer.getSurgeryHistoryList()) {
			surgeryHistory += "\n날짜 : " + surgery.getDate() + " 병원 이름 : " + surgery.getHospitalName();
		}
		String accidentHistory = "";
		for (AccidentHistory accident : customer.getAccidentHistoryList()) {
			accidentHistory += "\n날짜 : " + accident.getDate() + " 사고 내용 : " + accident.getAccidentDetail();
		}
		System.out.println(" 고객 이름 : " + customer.getName() + " 전화번호 : " + customer.getPhoneNumber() + " 직업 : "
				+ customer.getJob() + " 나이 : " + customer.getAge() + " 성별 : " + customer.getGender().getName()
				+ " 주민등록번호 : " + customer.getResidentRegistrationNumber() + " 주소 : " + customer.getAddress()
				+ " 계좌 번호 : " + customer.getBankAccount() + " 재산 : " + customer.getProperty() + "\n사고 이력 : "
				+ surgeryHistory + "\n수술 이력 : " + accidentHistory);
		try {
			do {
				System.out.println("1. 승인 2. 거절 3. 취소");
				index = Integer.parseInt(scanner.next());
				switch (index) {
				case 1:
					System.out.print("지급 금액 : ");
					int money = Integer.parseInt(scanner.next());
					PaymentType paymentType = getPaymentType();
					loanManagementModel.requestLoan(contract, customer, money, paymentType, true, this.contractList,
							this.paymentDetailList, this.compensationDetailList);
					break;
				case 2:
					loanManagementModel.requestLoan(contract, customer, 0, null, false, this.contractList, 
							this.paymentDetailList, this.compensationDetailList);
					break;
				case 3:
					break;
				default:
					System.out.print("명시된 번호중에 클릭해주세요.");
				}
			} while (!(1 <= index && index <= 3));
		} catch (NumberFormatException e) {
			//
		} catch (AlreadyProcessedException | NotExistContractException e) {
			System.out.println(e.getMessage());
		}
	}

//	2024-06-02 김대현
//  2024-06-04 김대현
	private void manageLoanProduct(Employee employee) {
		// 600130
		System.out.println("대출 상품 정보 리스트");
//		대출 상품 이름, 대출 상품 종류, 대출 상품 번호, 이자율, 대출가능 최대 금액
		for (Product e : productList.getAll()) {
			if (e instanceof Loan) {
				System.out.print("대출 상품 이름: " + ((Loan) e).getName() + " ");
				System.out.print("대출 종류: " + ((Loan) e).getLoanType().getName() + " ");
				System.out.print("대출 상품 번호: " + ((Loan) e).getId() + " ");
				System.out.print("이자율: " + ((Loan) e).getInterestRate() + " ");
				System.out.println("대출 가능 최대 금액: " + ((Loan) e).getLimit());
			}
		}

		System.out.println("1. 등록 2. 검색 3. 더블클릭");
		try {
			input = scanner.next();
			index = Integer.parseInt(input);
			switch (index) {
			case 1:
				System.out.println("대출 종류 콤보박스");
				System.out.println("1. 담보 대출 2. 정기 예금 대출 3. 보험 계약 대출");
				try {
					input = scanner.next();
					index = Integer.parseInt(input);
					switch (index) {
					case 1:
						createCollateralLoan(employee);
						break;
					case 2:
						createFixedDepositLoan(employee);
						break;
					case 3:
						createInsuranceContractLoan(employee);
						break;
					default:
						return;
					}
				} catch (NumberFormatException numberFormatException) {
					System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
					return;
				}
				break;
			case 2:
				getLoan(employee);
				break;
			case 3:
				doubleClickLoan(employee);
				break;
			default:
				return;
			}
		} catch (NumberFormatException numberFormatException) {
			System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
			return;
		}

	}

//	2024-06-02 김대현
//  2024-06-04 김대현
	private void createCollateralLoan(Employee employee) {
		// LoanManagementModel employee
		boolean finish = false;
		while (!finish) {
			try {
				System.out.println("1. 대출 종류 : 담보");
				LoanType loanType = LoanType.Collateral;
				System.out.print("2. 대출 상품 이름: ");
				input = scanner.next();
				String name = input;
				System.out.print("3. 이자율: ");
				input = scanner.next();
				int interestRate = Integer.parseInt(input);
				System.out.print("4. 대출가능 최대 금액: ");
				input = scanner.next();
				int limit = Integer.parseInt(input);
				System.out.print("5. 최소 자산: ");
				input = scanner.next();
				int minimumAsset = Integer.parseInt(input);

				CollateralType collateralType = null;
				System.out.println("6. 담보 종류: ");
				System.out.println("1.부동산 2. 자동차");
				input = scanner.next();
				switch (Integer.parseInt(input)) {
				case 1:
					collateralType = CollateralType.RealEstate;
					break;
				case 2:
					collateralType = CollateralType.Car;
					break;
				default:
					return;
				}

				System.out.print("7. 담보 최소 가치: ");
				input = scanner.next();
				int minimumValue = Integer.parseInt(input);

				do {
					System.out.println("1. 확인 2. 취소");
					input = scanner.next();
					index = Integer.parseInt(input);
					switch (index) {
					case 1:
						loanManagementModel.addLoanProduct(loanType, name, interestRate, 
								limit, minimumAsset, collateralType,
								minimumValue, productList);
						System.out.println("등록되었습니다.");
						finish = true;
						break;
					case 2:
						break;
					default:
						System.out.println("명시된 번호를 클릭해주세요.");
					}
				} while (!(1 <= index && index <= 2));
			} catch (DuplicateLoanException duplicateLoanExeption) {
				System.out.println(duplicateLoanExeption.getMessage());
				return;
			} catch (NumberFormatException numberFormatException) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
				return;
			}
		}

	}

//	2024-06-02 김대현
//  2024-06-04 김대현
	private void createFixedDepositLoan(Employee employee) {
		// LoanManagementModel employee
		boolean finish = false;
		while (!finish) {
			try {
				System.out.println("1. 대출 종류 : 정기 예금");
				LoanType loanType = LoanType.FixedDeposit;
				System.out.print("2. 대출 상품 이름: ");
				input = scanner.next();
				String name = input;
				System.out.print("3. 이자율: ");
				input = scanner.next();
				int interestRate = Integer.parseInt(input);
				System.out.print("4. 대출가능 최대 금액: ");
				input = scanner.next();
				int limit = Integer.parseInt(input);
				System.out.print("5. 최소 자산: ");
				input = scanner.next();
				int minimumAsset = Integer.parseInt(input);
				System.out.print("6. 최소 예치 금액: ");
				input = scanner.next();
				int minimumAmount = Integer.parseInt(input);

				do {
					System.out.println("1. 확인 2. 취소");
					input = scanner.next();
					index = Integer.parseInt(input);
					switch (index) {
					case 1:
						loanManagementModel.addLoanProduct(loanType, name, interestRate, 
								limit, minimumAsset, minimumAmount,
								productList);
						System.out.println("등록되었습니다.");
						finish = true;
						break;
					case 2:
						return;
					default:
						System.out.println("명시된 번호를 클릭해주세요.");
					}
				} while (!(1 <= index && index <= 2));
			} catch (DuplicateLoanException duplicateLoanExeption) {
				System.out.println(duplicateLoanExeption.getMessage());
				return;
			} catch (NumberFormatException numberFormatException) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
				return;
			}
		}
	}

//	2024-06-02 김대현
//  2024-06-04 김대현
	private void createInsuranceContractLoan(Employee employee) {
		// LoanManagementModel employee
		boolean finish = false;
		while (!finish) {
			try {
				System.out.println("1. 대출 종류 : 보험 계약 대출");
				LoanType loanType = LoanType.InsuranceContract;
				System.out.print("2. 대출 상품 이름: ");
				input = scanner.next();
				String name = input;
				System.out.print("3. 이자율: ");
				input = scanner.next();
				int interestRate = Integer.parseInt(input);
				System.out.print("4. 대출가능 최대 금액: ");
				input = scanner.next();
				int limit = Integer.parseInt(input);
				System.out.print("5. 최소 자산: ");
				input = scanner.next();
				int minimumAsset = Integer.parseInt(input);
				System.out.print("6. 보험 상품 번호: ");
				input = scanner.next();
				int productID = Integer.parseInt(input);

				do {
					System.out.println("1. 확인 2. 취소");
					input = scanner.next();
					index = Integer.parseInt(input);
					switch (index) {
					case 1:
						loanManagementModel.addLoanProduct(loanType, name, 
								interestRate, limit, minimumAsset, productID,
								productList);
						System.out.println("등록되었습니다.");
						finish = true;
						break;
					case 2:
						return;
					default:
						System.out.println("명시된 번호중에 클릭해주세요.");
					}
				} while (!(1 <= index && index <= 2));
			} catch (DuplicateLoanException duplicateLoanExeption) {
				System.out.println(duplicateLoanExeption.getMessage());
				return;
			} catch (NumberFormatException numberFormatException) {
				System.out.println("잘못된 정보를 입력하였습니다. 다시 입력해주세요.");
				return;
			}
		}
	}

//	2024-06-02 김대현
	private void getLoan(Employee employee) {
		// LoanManagementModel employee
		System.out.print("검색창: ");
		input = scanner.next();
		id = Integer.parseInt(input);
		Loan loan;
		try {
			loan = loanManagementModel.getLoanProduct(productList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.print("대출 상품 이름: " + loan.getName() + " ");
		System.out.print("대출 종류: " + loan.getLoanType().getName() + " ");
		System.out.print("대출 상품 번호: " + loan.getId() + " ");
		System.out.print("이자율: " + loan.getInterestRate() + " ");
		System.out.println("대출 가능 최대 금액: " + loan.getLimit());
	}

//	2024-06-02 김대현
	private void doubleClickLoan(Employee employee) {
		// LoanManagementModel employee
		System.out.println("대출 상품의 번호(더블클릭): ");
		input = scanner.next();
		id = Integer.parseInt(input);

		Loan loan;
		try {
			loan = loanManagementModel.getLoanProduct(productList, id);
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("1. 대출 상품 이름: " + loan.getName());
		System.out.println("2. 대출 종류: " + loan.getLoanType().getName());
		System.out.println("3. 대출 상품 번호: " + loan.getId());
		System.out.println("4. 이자율: " + loan.getInterestRate());
		System.out.println("5. 대출 가능 최대 금액: " + loan.getLimit());
		System.out.println("6. 최소 자산: " + loan.getMinimumAsset());
		if (loan instanceof Collateral) {
			Collateral collateralLoan = (Collateral) loan;
			System.out.println("7. 담보 종류: " + collateralLoan.getCollateralType().getName());
			System.out.println("8. 담보 최소 가치: " + collateralLoan.getMinimumValue());
		} else if (loan instanceof FixedDeposit) {
			FixedDeposit fixedDepositLoan = (FixedDeposit) loan;
			System.out.println("7. 최대 예치 금액: " + fixedDepositLoan.getMinimumAmount());
		} else if (loan instanceof InsuranceContract) {
			InsuranceContract lnsuranceContractLoan = (InsuranceContract) loan;
			System.out.println("7. 보험 상품 번호: " + lnsuranceContractLoan.getProductID());
		}

		System.out.println("1. 수정 2. 삭제");
		input = scanner.next();
		index = Integer.parseInt(input);
		switch (index) {
		case 1:
			updateLoan(employee, id);
			break;
		case 2:
			deleteLoan(employee, id);
			break;
		default:
			return;
		}

	}

//	2024-06-02 김대현
	private void updateLoan(Employee employee, int id) {
		// LoanManagementModel employee
		try {
			Loan loan = loanManagementModel.getLoanProduct(productList, id);
			System.out.println("대출 상품 번호: " + loan.getId());
			System.out.println("대출 종류: " + loan.getLoanType().getName());

			System.out.println("1. 대출 상품 이름: " + loan.getName());
			System.out.println("2. 이자율: " + loan.getInterestRate());
			System.out.println("3. 대출 가능 최대 금액: " + loan.getLimit());
			System.out.println("4. 최소 자산: " + loan.getMinimumAsset());

			if (loan instanceof Collateral) {
				updateCollateralLoan(employee, loan);
			} else if (loan instanceof FixedDeposit) {
				updateFixedDepositLoan(employee, loan);
			} else if (loan instanceof InsuranceContract) {
				updateInsuranceContract(employee, loan);
			}
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
	}

//	2024-06-02 김대현
//  2024-06-04 김대현
	private void updateCollateralLoan(Employee employee, Loan loan) {
		// LoanManagementModel employee
		Collateral collateralLoan = (Collateral) loan;
		System.out.println("5. 담보 종류: " + collateralLoan.getCollateralType().getName());
		System.out.println("6. 담보 최소 가치: " + collateralLoan.getMinimumValue());

		try {
			int inputIndex = 0;
			input = scanner.next();
			inputIndex = Integer.parseInt(input);
			if (!(1 <= inputIndex && inputIndex <= 6)) {
				return;
			} else if (inputIndex == 5) {
				System.out.println("5. 담보 종류");
				System.out.println("1.부동산 2. 자동차");
			}

			System.out.println(inputIndex + "번에 해당하는 새로운 값: ");
			String inputParameter = scanner.next();

			if (inputIndex == 5) {
				if (!(1 <= Integer.parseInt(inputParameter) && Integer.parseInt(inputParameter) <= 2)) {
					return;
				}
			}

			do {
				System.out.println("1. 수정 2. 취소");
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					do {
						System.out.println("정말로 수정하시겠습니까?");
						System.out.println("1. 확인 2. 취소");
						input = scanner.next();
						index = Integer.parseInt(input);
						switch (index) {
						case 1:
							loanManagementModel.updateLoanProduct(inputIndex, inputParameter, collateralLoan, productList);
							System.out.println("수정되었습니다.");
							break;
						case 2:
							return;
						default:
							System.out.println("명시된 번호중에 클릭해주세요.");
						}
					} while (!(1 <= index && index <= 2));
					return;
				case 2:
					return;
				default:
					System.out.println("명시된 번호중에 클릭해주세요.");
				}
			} while (!(1 <= index && index <= 2));
		} catch (DuplicateLoanException | NotExistException e) {
			System.out.println(e.getMessage());
		}
	}

//	2024-06-02 김대현
//  2024-06-04 김대현
	private void updateFixedDepositLoan(Employee employee, Loan loan) {
		// LoanManagementModel employee
		FixedDeposit fixedDepositLoan = (FixedDeposit) loan;
		System.out.println("5. 최대 예치 금액: " + fixedDepositLoan.getMinimumAmount());

		try {
			int inputIndex = 0;
			input = scanner.next();
			inputIndex = Integer.parseInt(input);
			if (!(1 <= inputIndex && inputIndex <= 5)) {
				return;
			}

			System.out.println(inputIndex + "번에 해당하는 새로운 값: ");
			String inputParameter = scanner.next();

			do {
				System.out.println("1. 수정 2. 취소");
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					do {
						System.out.println("정말로 수정하시겠습니까?");
						System.out.println("1. 확인 2. 취소");
						input = scanner.next();
						index = Integer.parseInt(input);
						switch (index) {
						case 1:
							loanManagementModel.updateLoanProduct(inputIndex, 
									inputParameter, fixedDepositLoan, productList);
							System.out.println("수정되었습니다.");
							break;
						case 2:
							return;
						default:
							System.out.println("명시된 번호중에 클릭해주세요.");
						}
					} while (!(1 <= index && index <= 2));
					return;
				case 2:
					return;
				default:
					System.out.println("명시된 번호중에 클릭해주세요.");
				}
			} while (!(1 <= index && index <= 2));
		} catch (DuplicateLoanException | NotExistException e) {
			System.out.println(e.getMessage());
		}
	}

//	2024-06-02 김대현
//  2024-06-04 김대현
	private void updateInsuranceContract(Employee employee, Loan loan) {
		// LoanManagementModel employee
		InsuranceContract lnsuranceContractLoan = (InsuranceContract) loan;
		System.out.println("5. 보험 상품 번호: " + lnsuranceContractLoan.getProductID());

		try {
			int inputIndex = 0;

			input = scanner.next();
			inputIndex = Integer.parseInt(input);
			if (!(1 <= inputIndex && inputIndex <= 5)) {
				return;
			}

			System.out.println(inputIndex + "번에 해당하는 새로운 값: ");
			String inputParameter = scanner.next();

			do {
				System.out.println("1. 수정 2. 취소");
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					do {
						System.out.println("정말로 수정하시겠습니까?");
						System.out.println("1. 확인 2. 취소");
						input = scanner.next();
						index = Integer.parseInt(input);
						switch (index) {
						case 1:
							loanManagementModel.updateLoanProduct(inputIndex, 
									inputParameter, lnsuranceContractLoan, productList);
							System.out.println("수정되었습니다.");
							break;
						case 2:
							return;
						default:
							System.out.println("명시된 번호중에 클릭해주세요.");
						}
					} while (!(1 <= index && index <= 2));
					return;
				case 2:
					return;
				default:
					System.out.println("명시된 번호중에 클릭해주세요.");
				}
			} while (!(1 <= index && index <= 2));
		} catch (DuplicateLoanException | NotExistException e) {
			System.out.println(e.getMessage());
		}
	}

//	2024-06-02 김대현
//  2024-06-04 김대현
	private void deleteLoan(Employee employee, int id) {
		// LoanManagementModel employee
		try {
			do {
				System.out.println("정말로 삭제하시겠습니까?");
				System.out.println("1. 확인 2. 취소");
				input = scanner.next();
				index = Integer.parseInt(input);
				switch (index) {
				case 1:
					loanManagementModel.deleteLoanProduct(productList, id);
					System.out.println("삭제되었습니다.");
					return;
				case 2:
					return;
				default:
					System.out.println("명시된 번호중에 클릭해주세요.");
				}
			} while (!(1 <= index && index <= 2));
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
	}

}