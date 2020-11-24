package br.com.fiap.handler;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import br.com.fiap.dao.TripRepository;
import br.com.fiap.model.HandlerRequest;
import br.com.fiap.model.HandlerResponse;
import br.com.fiap.model.Trip;

public class GetTripsRecordsByCountry implements RequestHandler<HandlerRequest, HandlerResponse> {

	private final TripRepository repository = new TripRepository();

	@Override
	public HandlerResponse handleRequest(HandlerRequest request, Context context) {

		context.getLogger().log("Searching for registered trips for ");
		
		final String country = request.getPathParameters().get("country");

		context.getLogger().log("Searching for registered trips for " + country);

		final List<Trip> trips = this.repository.findByCountry(country);

		if (trips == null || trips.isEmpty()) {
			return HandlerResponse.builder().setStatusCode(404).build();
		}
		context.getLogger().log(trips.toString());
		return HandlerResponse.builder().setStatusCode(200).setObjectBody(trips).build();
	}
}