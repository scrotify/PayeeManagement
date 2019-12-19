package com.scrotifybanking.payeemanagement.util;

/**
 * The type Scrotify constant.
 */
public final class ScrotifyConstant {

	/**
	 * The constant SUCCESS_MESSAGE.
	 */
	public static final String SUCCESS_MESSAGE = "SUCCESS";

	/**
	 * The constant FAILURE_REGISTRATION
	 */
	public static final String FAILURE_REGISTRATION_MESSAGE = "Already Registered";
	/**
	 * The constant Failure_Registration_code
	 */
	public static final int FAILURE_REGISTRATION_CODE = 401;
	/**
	 * The constant CREDIT_TRANSACTION.
	 */
	public static final String CREDIT_TRANSACTION = "CR";
	/**
	 * The constant DEBIT_TRANSACTION.
	 */
	public static final String DEBIT_TRANSACTION = "DR";
	/**
	 * The constant SUCCESS_CODE.
	 */
	public static final int SUCCESS_CODE = 200;
	/**
	 * The constant AGE_LIMIT.
	 */
	public static final int AGE_LIMIT = 18;
	/**
	 * The constant AMOUNT.
	 */
	public static final double AMOUNT = 10000;

	/**
	 * The constant ACCOUNT_ACTIVE_STATUS.
	 */
	public static final String ACCOUNT_ACTIVE_STATUS = "ACTIVE";
	/**
	 * The constant ACCOUNT_TYPE.
	 */
	public static final String ACCOUNT_TYPE = "Savings";
	/**
	 * The constant FUND_TRANSFER_FAILED.
	 */
	public static final String FUND_TRANSFER_FAILED = "Fund Transfer Failed";
	/**
	 * The constant FUND_TRANSFER_SUCCESS.
	 */
	public static final String FUND_TRANSFER_SUCCESS = "Fund Transfer Success";
	/**
	 * The constant MINIMUM_BALANCE_FAILED.
	 */
	public static final String MINIMUM_BALANCE_FAILED = "Minimum balance not found";
	/**
	 * The constant MAINTAIN_BALANCE_FAILED.
	 */
	public static final String MAINTAIN_BALANCE_FAILED = "Maintain minimum balance";

	/**
	 * The constant CUSTOMER_ID_NOT_FOUND.
	 */
	public static final String CUSTOMER_ID_NOT_FOUND = "Customer ID not found";
	/**
	 * The constant MINIMUM_BALANCE_MAINTAIN.
	 */
	public static final double MINIMUM_BALANCE_MAINTAIN = 5000;
	/**
	 * The constant TRANSACTION_FAILED.
	 */
	public static final int TRANSACTION_FAILED = 1;

	/**
	 * The constant FAILURE_MESSAGE.
	 */
	public static final String FAILURE_MESSAGE = "Registration is not appicable";

	/**
	 * The constant NOT_FOUND_MESSAGE.
	 */
	public static final String INVALID_MESSAGE = "Invalid Username or Password";
	/**
	 * The constant FAILURE_CODE.
	 */
	public static final int FAILURE_CODE = 401;
	/**
	 * The constant SAVINGS_ACCOUNT_MESSAGE.
	 */
	public static final String SAVINGS_ACCOUNT_MESSAGE = "Savings";
	/**
	 * The constant MORTGAGE_ACCOUNT_MESSAGE.
	 */
	public static final String MORTGAGE_ACCOUNT_MESSAGE = "Mortgage";
	/**
	 * The constant NOT_FOUND_CODE.
	 */
	public static final int NOT_FOUND_CODE = 404;

	/**
	 * The constant ACCOUNT_CREATED_MESSAGE.
	 */
	public static final String ACCOUNT_CREATED_MESSAGE = "Account created successfully";
	/**
	 * The constant CREATED_CODE.
	 */
	public static final int CREATED_CODE = 201;
	/**
	 * The constant ACCOUNT_CREATED_MESSAGE.
	 */
	public static final String ADMIN_MESSAGE = "admin";

	/**
	 * The constant BENEFICIARY_MESSAGE.
	 */
	public static final String BENEFICIARY_MESSAGE = "Beneficiary Account created successfully";
	/**
	 * The constant BENEFICIARY_MESSAGE.
	 */
	public static final String BENEFICIARY_EXCEED = "Number of beneficiary account exceeded for your account";
	/**
	 * The constant INVALID_BANK.
	 */
	public static final String INVALID_BANK = "Invalid ifsc code for bank name ";
	
	public static final String NO_BENEFICIARY_ADDED = "No beneficiaries have added";

	public static final String MORTGAGE_ACCOUNT_TYPE = "Mortgage";

	public static final String ACCOUNT_NOT_FOUND = "Account number not found";
	
	public static final String INVALID_BANK_MESSAGE ="invalid bank name/bank name doesn't exit";
	public static final String UPDATED="updated successfully";
	public static final String INVALID_IFSC_CODE_MESSAGE ="invalid ifsc code/ifsc code doesn't exit";
	public static final String NO_BENEFICIARY="customer doesn't have beneficiary details";
	public static final String NO_BANK="Bank not found";

	public static final String EMPTY_IFSC_CODE = "you are leaving empty, please provide the ifsc code";

	public static final String EMPTY_BANK = "you are leaving empty, please provide the bank name";

	public static final String EMPTY_AMOUNT_LIMIT = "you are leaveing empty";

	public static final String EMPTY_ACCOUNT_NO = "you are leaveing empty, please enter the account number";

	private ScrotifyConstant() {

	}
}
