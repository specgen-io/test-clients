package services

import "cloud.google.com/go/civil"
import "github.com/google/uuid"
import "github.com/shopspring/decimal"
import "test-service/spec"

type CheckService struct{}

func (service *CheckService) CheckEmpty() (*spec.CheckEmptyResponse, error) {
	return &spec.CheckEmptyResponse{Ok: &spec.Empty}, nil
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
	pEnum spec.Choice,
	pStringDefaulted string) (*spec.CheckQueryResponse, error) {

	return &spec.CheckQueryResponse{Ok: &spec.Empty}, nil
}
func (service *CheckService) CheckUrlParams(
	intUrl int64,
	stringUrl string,
	floatUrl float32,
	boolUrl bool,
	uuidUrl uuid.UUID,
	decimalUrl decimal.Decimal,
	dateUrl civil.Date) (*spec.CheckUrlParamsResponse, error) {

	return &spec.CheckUrlParamsResponse{Ok: &spec.Empty}, nil
}
func (service *CheckService) CheckForbidden() (*spec.CheckForbiddenResponse, error) {
	return &spec.CheckForbiddenResponse{Forbidden: &spec.Empty}, nil
}
