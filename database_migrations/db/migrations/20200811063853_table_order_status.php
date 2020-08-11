<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class TableOrderStatus extends AbstractMigration
{

    public function change(): void
    {
        $orderStatus= $this->table('order_status', ['signed' => false]);
        $orderStatus->addColumn('title', 'string', ['limit' => 55])
            ->create();
    }
}
