package com.destiny2wishlist.ui;

/*@Route
@PWA(name = "Destiny 2 Wishlist Generator", shortName = "D2 Wishlist")
@CssImport("./styles/styles.css")
@Slf4j*/
public class MainView /*extends VerticalLayout*/ {

    /*private final DestinyManifestService manifestService;

    private final Grid<DestinyWeapon> grid;

    private static final Set<Grid.Column<DestinyWeapon>> collapsibleColumns = new LinkedHashSet();

    public MainView(DestinyManifestService manifestService) {
        VaadinSession.getCurrent()
                .setErrorHandler((ErrorHandler) errorEvent -> {
                    log.error("Uncaught UI exception",
                            errorEvent.getThrowable());
                    Notification.show(
                            "We are sorry, but an internal error occurred");
                });

        this.manifestService = manifestService;

        setSizeFull();
        setMargin(false);
        setSpacing(false);

        //TODO Load Destiny Manifest
        add(buildToolbar());

        grid = buildGrid();
        addAndExpand(grid);

        //loadDestinyManifest();
        //initSearchBar();
    }

    private void loadDestinyManifest() {
        *//*try {
            manifestService.loadDestinyManifest();
        } catch (ApiClientException e) {
            log.error("Error getting Destiny manifest", e);
        }*//*
    }

    private void initSearchBar() {
        ComboBox<DestinyWeapon> searchComboBox = new ComboBox<>("Select your weapon");
        searchComboBox.setPlaceholder("No weapon selected");

        // Disallow null selections
        searchComboBox.setPreventInvalidInput(true);
        // Disallow custom values
        searchComboBox.setAllowCustomValue(false);
        // Set full width
        searchComboBox.setWidth("70%");
        searchComboBox.setClearButtonVisible(true);
        searchComboBox.setAutofocus(true);

        //Populate with data
        List<DestinyWeapon> allWeapons = manifestService.getAllWeapons();
        ListDataProvider<DestinyWeapon> destinyWeaponListDataProvider = DataProvider.ofCollection(allWeapons);
        destinyWeaponListDataProvider.setSortOrder(DestinyWeapon::getName, SortDirection.ASCENDING);
        searchComboBox.setDataProvider(destinyWeaponListDataProvider);
        searchComboBox.setItemLabelGenerator(DestinyWeapon::getName);




        add(searchComboBox);
        setAlignItems(Alignment.CENTER);
    }

    private Component buildToolbar() {
        HorizontalLayout header = new HorizontalLayout();

        Label title = new Label("Destiny Weapons");
        title.setSizeUndefined();
        //TODO add H1 and NO_MARGIN style to title
        header.add(title);

        HorizontalLayout tools = new HorizontalLayout(buildFilter());
        header.add(tools);

        return header;
    }

    private Component buildFilter() {
        final TextField filter = new TextField();

        //TODO add value change listener to filter

        filter.setPlaceholder("Filter");
        filter.setWidth("80%");

        //TODO add shortcut listener to filter

        return filter;
    }

    private Grid<DestinyWeapon> buildGrid() {
        final Grid<DestinyWeapon> grid = new Grid<>();
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.setSizeFull();

        Grid.Column<DestinyWeapon> destinyWeaponColumn = grid.addColumn(DestinyWeapon::getName);
        destinyWeaponColumn.setId("Name");
        collapsibleColumns.add(destinyWeaponColumn);
        //TODO display weapon picture on grid
        //TODO add more columns to grid

        grid.setColumnReorderingAllowed(false);

        return grid;
    }*/
}
