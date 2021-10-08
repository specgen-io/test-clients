package services

import "cloud.google.com/go/civil"
import "github.com/google/uuid"
import "github.com/shopspring/decimal"
import "test-service/spec/check"
import "test-service/spec/models"

type CheckService struct{}

func (service *CheckService) CheckEmpty() (*check.CheckEmptyResponse, error) {
	return &check.CheckEmptyResponse{Ok: &check.Empty}, nil
}
func (service *CheckService) CheckQuery(pString string,
	pStringOpt *string,
	pStringArray []string,
	pDate civil.Date,
	pDateArray []civil.Date,
	pDatetime civil.DateTime,
	pInt int,
	pLong int64,
	pDecimal decimal.Decimal,
	pEnum models.Choice,
	pStringDefaulted string) (*check.CheckQueryResponse, error) {

	return &check.CheckQueryResponse{Ok: &check.Empty}, nil
}
func (service *CheckService) CheckUrlParams(
	intUrl int64,
	stringUrl string,
	floatUrl float32,
	boolUrl bool,
	uuidUrl uuid.UUID,
	decimalUrl decimal.Decimal,
	dateUrl civil.Date) (*check.CheckUrlParamsResponse, error) {

	return &check.CheckUrlParamsResponse{Ok: &check.Empty}, nil
}
func (service *CheckService) CheckForbidden() (*check.CheckForbiddenResponse, error) {
	return &check.CheckForbiddenResponse{Forbidden: &check.Empty}, nil
}

func (service *CheckService) SameOperationName() (*check.SameOperationNameResponse, error) {
	return &check.SameOperationNameResponse{Ok: &check.Empty}, nil
}
